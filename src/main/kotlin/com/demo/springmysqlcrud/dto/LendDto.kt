package com.demo.springmysqlcrud.dto

import com.demo.springmysqlcrud.enumeration.LendStatus
import java.time.Instant

class LendDto {
    data class Response (
        val id: Long,
        val status: LendStatus,
        val startOn: Instant,
        val dueOn: Instant
    ) {

    }

    data class Request (
        val bookIds: List<Long>,
        val memberId: Long
    )
}
