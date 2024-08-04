package com.iyam.kotlin_restful_api.controller

import com.iyam.kotlin_restful_api.error.NotFoundException
import com.iyam.kotlin_restful_api.error.UnauthorizedException
import com.iyam.kotlin_restful_api.model.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(ConstraintViolationException::class)
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            400,
            "Bad Request",
            data = constraintViolationException.message ?: ""
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(exception: NotFoundException): WebResponse<String> {
        return WebResponse(
            404,
            "Not Found",
            data = "Data not found"
        )
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedException(exception: UnauthorizedException): WebResponse<String> {
        return WebResponse(
            401,
            "Unauthorized",
            data = "Please put your X-Api-Key information"
        )
    }
}