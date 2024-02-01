package com.demo.springmysqlcrud.controller

import com.demo.springmysqlcrud.dto.AuthorDto
import com.demo.springmysqlcrud.dto.BookDto
import com.demo.springmysqlcrud.dto.LendDto
import com.demo.springmysqlcrud.dto.MemberDto
import com.demo.springmysqlcrud.service.LibraryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RestController
@RequestMapping("/api/library")
class LibraryController(
    private val libraryService: LibraryService
) {

    @GetMapping("/book")
    fun readBooks(@RequestParam(required = false) isbn: String?): ResponseEntity<*> {
        isbn?.let {
            return ResponseEntity.ok(libraryService.readBook(isbn))
        } ?: return ResponseEntity.ok(libraryService.readBooks())
    }

    @GetMapping("/book/{bookId}")
    fun readBook(@PathVariable bookId: Long): ResponseEntity<BookDto.Response> {
        return ResponseEntity.ok(libraryService.readBook(bookId))
    }

    @PostMapping("/book")
    fun createBook(@RequestBody request: BookDto.Request): ResponseEntity<BookDto.Response> {
        return ResponseEntity.ok(libraryService.createBook(request))
    }

    @PutMapping("/book/{bookId}")
    fun updateBook(
        @PathVariable("bookId") bookId: Long,
        @RequestBody request: BookDto.Request
    ): ResponseEntity<BookDto.Response> {
        return ResponseEntity.ok(libraryService.updateBook(bookId, request))
    }

    @PostMapping("/author")
    fun createAuthor(@RequestBody request: AuthorDto.Request): ResponseEntity<AuthorDto.Response> {
        return ResponseEntity.ok(libraryService.createAuthor(request))
    }

    @GetMapping("/author")
    fun readAuthors(): ResponseEntity<List<AuthorDto.Response>> {
        return ResponseEntity.ok(libraryService.readAuthors())
    }

    @DeleteMapping("/book/{bookId}")
    fun deleteBook(@PathVariable bookId: Long): ResponseEntity<Void> {
        libraryService.deleteBook(bookId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/member")
    fun createMember(@RequestBody request: MemberDto.Request): ResponseEntity<MemberDto.Response> {
        return ResponseEntity.ok(libraryService.createMember(request))
    }

    @GetMapping("/member")
    fun readMembers(): ResponseEntity<List<MemberDto.Response>> {
        return ResponseEntity.ok(libraryService.readMembers())
    }

    @PutMapping("/member/{memberId}")
    fun updateMember(
        @RequestBody request: MemberDto.Request,
        @PathVariable memberId: Long
    ): ResponseEntity<MemberDto.Response> {
        return ResponseEntity.ok(libraryService.updateMember(memberId, request))
    }

    @PostMapping("/book/lend")
    fun lendABook(@RequestBody request: LendDto.Request): ResponseEntity<List<String>> {
        return ResponseEntity.ok(libraryService.lendBook(request))
    }
}