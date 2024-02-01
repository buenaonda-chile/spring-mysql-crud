package com.demo.springmysqlcrud.repository

import com.demo.springmysqlcrud.entity.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: JpaRepository<Author, Long> {
}