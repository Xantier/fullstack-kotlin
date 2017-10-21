package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


fun BeanDefinitionDsl.securityBeans(paths: ServerHttpSecurity.AuthorizeExchangeBuilder
.(SecurityService) -> ServerHttpSecurity.AuthorizeExchangeBuilder) {
    bean<UserRepository>()
    bean<UserService> {
        UserServiceImpl(ref())
    }
    bean<SecurityService> {
        SecurityServiceImpl()
    }
    bean {
        ReactiveUserDetailsService { username ->
            ref<UserService>().getUserByName(username)
                ?.toUserDetails()
                ?.toMono()
                ?:
                Mono.empty()

        }
    }
    bean<SecurityWebFilterChain> {
        ServerHttpSecurity.http().authorizeExchange()
            .paths(ref())
            .anyExchange().authenticated()
            .and()
            .authenticationManager(UserDetailsRepositoryReactiveAuthenticationManager(ref()))
            .formLogin()
            .and()
            .build()
    }
}