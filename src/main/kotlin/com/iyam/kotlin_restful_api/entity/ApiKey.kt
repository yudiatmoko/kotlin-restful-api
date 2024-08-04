package com.iyam.kotlin_restful_api.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "API_KEY")
data class ApiKey(
    @Id
    val apikey: String,
)
