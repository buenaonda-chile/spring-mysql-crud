package com.demo.springmysqlcrud.repository

import com.demo.springmysqlcrud.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long> {
    fun findByIsbn(isbn: String): Book?
}