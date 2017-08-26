package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProjectConfig {
    @Bean
    fun helloSayer(): ProjectService = ProjectServiceImpl()
}