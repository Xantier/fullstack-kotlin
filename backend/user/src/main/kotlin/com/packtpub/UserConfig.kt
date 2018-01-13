package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


fun BeanDefinitionDsl.securityBeans() {
    bean<SecurityWebFilterChain>{
        ServerHttpSecurity.http().authorizeExchange()
            .pathMatchers(HttpMethod.GET, "/api/projects/**").permitAll()
            .pathMatchers(HttpMethod.GET, "/projects/**").hasRole("ADMIN")
            .pathMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
            .anyExchange().authenticated()
            .and()
            .build()
    }
}