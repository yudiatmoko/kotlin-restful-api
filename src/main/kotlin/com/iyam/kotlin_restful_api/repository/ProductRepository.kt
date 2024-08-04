package com.iyam.kotlin_restful_api.repository

import com.iyam.kotlin_restful_api.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, String> {

}