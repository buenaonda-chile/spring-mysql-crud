package com.demo.springmysqlcrud.dto

import com.demo.springmysqlcrud.entity.Book

class BookDto {
    data class Response(
        val id: Long,
        val name: String,
        val isbn: String,
        val author: AuthorDto.Response,
        val lends: List<LendDto.Response>
    ) {
        companion object {
            fun of(book: Book): Response {
                val author: AuthorDto.Response = AuthorDto.Response(book.author.firstName, book.author.lastName)
                val lends: List<LendDto.Response> =
                    book.lends.stream().map { i -> LendDto.Response(i.id!!, i.status, i.startOn, i.dueOn) }.toList()

                return Response(book.id!!, book.name, book.isbn, author, lends)
            }
        }
    }

    data class Request(
        val name: String,
        val isbn: String,
        val authorId: Long
    ) {

    }
}
