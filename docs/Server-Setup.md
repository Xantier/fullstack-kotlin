# Installing needed dependencies to a pristine Ubuntu 16.04 box


## Install needed application from package manager

1. Certbot to handle our `let's encrypt` SSL certificates 
    * `sudo add-apt-repository ppa:certbot/certbot`
    * `sudo apt-get update`
    * `sudo apt-get install certbot`
    
2. Nginx to serve as our web server / reverse proxy
    * `sudo apt install nginx`
    
3. JRE to run our Java application
    * `sudo apt install openjdk-8-jre`
    
    
    
## Install SSL certs using Let's Encrypt
    
Run command `sudo certbot certonly` and follow instructions on the screen. Note that you need to know your domain name at this point and it needs to point to your publicly exposed IP address. You also need to open port 443 which Amazon doesn't expose by default
  
  
## Setup Nginx to act as a reverse proxy for our application  
    
* Generate dhparam.pem file: `sudo openssl dhparam -out /etc/ssl/certs/dhparam.pem 2048`

* Create SSL configuration snippet for your domain: `sudo nano /etc/nginx/snippets/ssl-yourdomain.com.conf`
    * Contents:
    ```
    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;
    ```
* Create SSL params snippet for your domain: `sudo nano /etc/nginx/snippets/ssl-params.conf`
    * Contents:
    ```
    # from https://cipherli.st/
    # and https://raymii.org/s/tutorials/Strong_SSL_Security_On_nginx.html
    
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_prefer_server_ciphers on;
    ssl_ciphers "EECDH+AESGCM:EDH+AESGCM:AES256+EECDH:AES256+EDH";
    ssl_ecdh_curve secp384r1;
    ssl_session_cache shared:SSL:10m;
    ssl_session_tickets off;
    ssl_stapling on;
    ssl_stapling_verify on;
    resolver 8.8.8.8 8.8.4.4 valid=300s;
    resolver_timeout 5s;
    # disable HSTS header for now
    #add_header Strict-Transport-Security "max-age=63072000; includeSubDomains; preload";
    add_header X-Frame-Options DENY;
    #add_header X-Content-Type-Options nosniff;
    
    ssl_dhparam /etc/ssl/certs/dhparam.pem;
    ```

* Create the actual Nginx configuration (backup first: `sudo cp /etc/nginx/sites-available/default /etc/nginx/sites-available/default.bak`): `sudo nano /etc/nginx/sites-available/default`
    * Contents:
    ```
    upstream netty {
            server localhost:8080;
    }
    server {
            listen 80 default_server;
            listen [::]:80 default_server;
            server_name yourdomain.com www.yourdomain.com localhost;
            return 301 https://$server_name$request_uri;
    }
    
    server {
    
            # SSL configuration
    
            listen 443 ssl http2 default_server;
            listen [::]:443 ssl http2 default_server;
            include snippets/ssl-yourdomain.com.conf;
            include snippets/ssl-params.conf;
            location / {
                    proxy_pass       http://netty$request_uri;
                    proxy_set_header Host $host;
                    proxy_set_header X-Real-IP $remote_addr;
                    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                    proxy_set_header X-Forwarded-Host $server_name;
            }
    }

    ```

* Test Nginx config and restart: `sudo nginx -t` -> `sudo systemctl restart nginx` 

## Set up our application to be run as a service

* Create a user: `sudo adduser kotliner`
* Modify file owners: `sudo chown kotliner fullstack-kotlin.jar` & `sudo chown kotliner application.properties`   
* Create a service definition to start the application as a service: `sudo nano /etc/systemd/system/fullstack-kotlin.service`
    * Contents: 
    ```
    [Unit]
    Description=Fullstack-Kotlin
    
    [Service]
    User=kotliner
    # The configuration file application.properties should be here:
    WorkingDirectory=/home/ubuntu/app
    ExecStart=/usr/bin/java -Xmx256m -jar fullstack-kotlin.jar
    SuccessExitStatus=143
    TimeoutStopSec=10
    Restart=on-failure
    RestartSec=5
    
    [Install]
    WantedBy=multi-user.target
    ```
* Start our application as a service: `sudo systemctl start fullstack-kotlin`    
* Logs can be found from journactl: `sudo journalctl -f -u fullstack-kotlin.service`


More info:

https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-on-ubuntu-14-04 
