package com.demo.springmysqlcrud.repository

import com.demo.springmysqlcrud.entity.Book
import com.demo.springmysqlcrud.entity.Lend
import com.demo.springmysqlcrud.enumeration.LendStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LendRepository: JpaRepository<Lend, Long>{
    fun findByBookAndStatus(book: Book, status: LendStatus): Lend?
}