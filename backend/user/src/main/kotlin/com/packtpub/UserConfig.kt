package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UserDetailsRepositoryAuthenticationManager
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.WebFilterChainFilter
import org.springframework.security.web.server.context.WebSessionSecurityContextRepository
import org.springframework.web.server.WebFilter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class UserConfig(private val applicationContext: GenericApplicationContext) {

    @Primary
    @Bean
    fun userService(): UserService =
        UserServiceImpl(applicationContext.getBean(UserRepository::class.java))
}


fun BeanDefinitionDsl.securityBeans() {
    bean<SecurityWebFilterChain> {
        HttpSecurity.http().authorizeExchange()
            .pathMatchers(HttpMethod.GET, "/api/projects/**").permitAll()
            .pathMatchers(HttpMethod.GET, "/login").permitAll()
            .pathMatchers(HttpMethod.POST, "/login").permitAll()
            .pathMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and()
            .build()
    }
    bean<WebFilter>("springSecurityFilterChain") {
        WebFilterChainFilter(Flux.just(it.ref()))
    }
    bean<HttpSecurity> {
        HttpSecurity.http().apply {
            httpBasic()
            authenticationManager(UserDetailsRepositoryAuthenticationManager(it.ref()))
            securityContextRepository(WebSessionSecurityContextRepository())
        }
    }

    bean {
        UserDetailsRepository { username ->
            Mono.just(it.ref<UserRepository>()
                .findByUsername(username)
                .toUserDetails()
            )
        }
    }
}

private fun com.packtpub.PacktUser.toUserDetails(): UserDetails =
    User.withUsername(username)
        .password(password)
        .accountExpired(!active)
        .accountLocked(!active)
        .credentialsExpired(!active)
        .disabled(!active)
        .authorities(roles.map(::SimpleGrantedAuthority).toList())
        .build()

