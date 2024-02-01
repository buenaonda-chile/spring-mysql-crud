package com.demo.springmysqlcrud.repository

import com.demo.springmysqlcrud.entity.Member
import com.demo.springmysqlcrud.enumeration.MemberStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun findByIdAndStatus(id: Long, status: MemberStatus): Member?
}