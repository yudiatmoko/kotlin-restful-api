package com.iyam.kotlin_restful_api.config

import com.iyam.kotlin_restful_api.entity.ApiKey
import com.iyam.kotlin_restful_api.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeyConfiguration(
    val repository: ApiKeyRepository
): ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!repository.existsById(apiKey)){
            val newApiKey = ApiKey(apiKey)
            repository.save(newApiKey)
        }
    }
}