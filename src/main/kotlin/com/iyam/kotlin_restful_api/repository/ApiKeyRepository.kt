package com.iyam.kotlin_restful_api.repository

import com.iyam.kotlin_restful_api.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKey, String> {
}