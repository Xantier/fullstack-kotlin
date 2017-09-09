package com.packtpub

import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.projectBeans() {
    bean<ProjectRepository>()
    bean<ProjectService> { ProjectServiceImpl(it.ref()) }
}
