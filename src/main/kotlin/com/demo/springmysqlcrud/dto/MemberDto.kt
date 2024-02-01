package com.demo.springmysqlcrud.dto

import com.demo.springmysqlcrud.enumeration.MemberStatus

class MemberDto {
    data class Response (
        val firstName: String,
        val lastName: String,
        val status: MemberStatus
    ) {

    }

    data class Request(
        val firstName: String,
        val lastName: String
    )
}