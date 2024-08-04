package com.iyam.kotlin_restful_api.service

import com.iyam.kotlin_restful_api.entity.Product
import com.iyam.kotlin_restful_api.error.NotFoundException
import com.iyam.kotlin_restful_api.model.CreateProductRequest
import com.iyam.kotlin_restful_api.model.ListProductRequest
import com.iyam.kotlin_restful_api.model.ProductResponse
import com.iyam.kotlin_restful_api.model.UpdateProductRequest
import com.iyam.kotlin_restful_api.repository.ProductRepository
import com.iyam.kotlin_restful_api.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date
import java.util.stream.Collectors

interface ProductService {
    fun create(request: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, request: UpdateProductRequest): ProductResponse
    fun delete(id: String)
    fun list(request: ListProductRequest): List<ProductResponse>
}

@Service
class ProductServiceImpl(
    val repository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {
    override fun create(request: CreateProductRequest): ProductResponse {
        validationUtil.validate(request)

        val product = Product(
            id = request.id ?: "",
            name = request.name ?: "",
            price = request.price ?: 0,
            quantity = request.quantity ?: 0,
            createdAt = Date(),
            updatedAt = null,
        )
        repository.save(product)
        return productToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductById(id)
        return productToProductResponse(product)
    }

    override fun update(id: String, request: UpdateProductRequest): ProductResponse {
        validationUtil.validate(request)
        val product = findProductById(id)

        product.apply {
            name = request.name ?: ""
            price = request.price ?: 0
            quantity = request.quantity ?: 0
            updatedAt = Date()
        }

        repository.save(product)
        return productToProductResponse(product)
    }

    override fun delete(id: String) {
        repository.delete(findProductById(id))
    }

    override fun list(request: ListProductRequest): List<ProductResponse> {
        val page = repository.findAll(PageRequest.of(request.page, request.pageSize))
        val products = page.get().collect(Collectors.toList())
        return products.map {
            productToProductResponse(it)
        }
    }

    private fun findProductById(id: String): Product{
        val product = repository.findByIdOrNull(id) ?: throw NotFoundException()
        return product
    }

    private fun productToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            product.id,
            product.name,
            product.price,
            product.quantity,
            product.createdAt,
            product.updatedAt
        )
    }
}