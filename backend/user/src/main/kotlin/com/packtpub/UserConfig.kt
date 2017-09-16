package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.security.authentication.UserDetailsRepositoryAuthenticationManager
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


fun BeanDefinitionDsl.securityBeans(paths: HttpSecurity.AuthorizeExchangeBuilder
.(SecurityService) -> HttpSecurity.AuthorizeExchangeBuilder) {
    bean<UserRepository>()
    bean<UserService> {
        UserServiceImpl(ref())
    }
    bean<SecurityService> {
        SecurityServiceImpl()
    }
    bean {
        UserDetailsRepository { username ->
            ref<UserService>().getUserByName(username)
                ?.toUserDetails()
                ?.toMono()
                ?:
                Mono.empty()

        }
    }
    bean<SecurityWebFilterChain> {
        HttpSecurity.http().authorizeExchange()
            .paths(ref())
            .anyExchange().authenticated()
            .and()
            .authenticationManager(UserDetailsRepositoryAuthenticationManager(ref()))
            .formLogin()
            .and()
            .build()
    }
}