package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@EnableWebFluxSecurity
class SecurityConfig

fun BeanDefinitionDsl.securityBeans(paths: HttpSecurity.AuthorizeExchangeBuilder.(SecurityService) -> HttpSecurity.AuthorizeExchangeBuilder) {
    bean<UserRepository>()
    bean<UserService> {
        UserServiceImpl(ref())
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
    bean<SecurityService> {
        SecurityServiceImpl()
    }
    bean<SecurityWebFilterChain> {
        ref<HttpSecurity>().authorizeExchange()
            .paths(ref())
            .anyExchange().authenticated()
            .and()
            .formLogin()
            .and()
            .build()
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

