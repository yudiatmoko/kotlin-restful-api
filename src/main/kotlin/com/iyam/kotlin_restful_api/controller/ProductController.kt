package com.iyam.kotlin_restful_api.controller

import com.iyam.kotlin_restful_api.model.*
import com.iyam.kotlin_restful_api.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    val service: ProductService
) {

    @PostMapping(
        value = ["api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(
        @RequestBody
        request: CreateProductRequest
    ): WebResponse<ProductResponse>{
        val productResponse = service.create(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id_product") id: String): WebResponse<ProductResponse> {
        val productResponse = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["api/products/{id_product}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("id_product") id: String,
                      @RequestBody request: UpdateProductRequest): WebResponse<ProductResponse> {
        val productResponse = service.update(id, request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("id_product") id: String): WebResponse<String> {
        service.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = id
        )
    }

    @GetMapping(
        value = ["api/products"],
        produces = ["application/json"]
    )
    fun listProducts(@RequestParam("size", defaultValue = "10") size: Int,
                     @RequestParam("page", defaultValue = "0") pageSize: Int): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(size, pageSize)
        val response = service.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}