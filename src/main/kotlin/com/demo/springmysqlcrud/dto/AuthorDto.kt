package com.demo.springmysqlcrud.dto

class AuthorDto {
    data class Response (
        val firstName: String,
        val lastName: String
    ) {

    }

    data class Request(
        val firstName: String,
        val lastName: String
    )
}