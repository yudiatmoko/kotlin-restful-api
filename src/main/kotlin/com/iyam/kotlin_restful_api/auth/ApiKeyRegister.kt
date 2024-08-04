package com.iyam.kotlin_restful_api.auth

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class ApiKeyRegister(
    val apiKeyInterceptor: ApiKeyInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addWebRequestInterceptor(apiKeyInterceptor)
    }
}