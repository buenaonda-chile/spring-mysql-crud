package com.demo.springmysqlcrud.service

import com.demo.springmysqlcrud.dto.AuthorDto
import com.demo.springmysqlcrud.dto.BookDto
import com.demo.springmysqlcrud.dto.LendDto
import com.demo.springmysqlcrud.dto.MemberDto
import com.demo.springmysqlcrud.entity.Author
import com.demo.springmysqlcrud.entity.Book
import com.demo.springmysqlcrud.entity.Lend
import com.demo.springmysqlcrud.entity.Member
import com.demo.springmysqlcrud.enumeration.LendStatus
import com.demo.springmysqlcrud.enumeration.MemberStatus
import com.demo.springmysqlcrud.repository.AuthorRepository
import com.demo.springmysqlcrud.repository.BookRepository
import com.demo.springmysqlcrud.repository.LendRepository
import com.demo.springmysqlcrud.repository.MemberRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LibraryService(
    private val authorRepository: AuthorRepository,
    private val memberRepository: MemberRepository,
    private val lendRepository: LendRepository,
    private val bookRepository: BookRepository
) {

    fun readBook(id: Long): BookDto.Response {
        val book: Book? = bookRepository.findByIdOrNull(id)

        book?.let {
            return BookDto.Response.of(book)
        } ?: throw EntityNotFoundException("")
    }

    fun readBook(isbn: String): BookDto.Response {
        val book: Book? = bookRepository.findByIsbn(isbn)

        book?.let {
            return BookDto.Response.of(book)
        } ?: throw EntityNotFoundException("")
    }

    fun readBooks(): List<BookDto.Response> {
        val books: List<Book> = bookRepository.findAll()

        return books.stream().map { i -> BookDto.Response.of(i) }.toList()
    }

    fun createBook(request: BookDto.Request): BookDto.Response {
        val author: Author? = authorRepository.findByIdOrNull(request.authorId)

        author?.let {

            val book: Book = bookRepository.save(Book(request.name, request.isbn, author))
            return BookDto.Response.of(book)

        } ?: throw EntityNotFoundException("")
    }

    fun deleteBook(id: Long) {
        bookRepository.deleteById(id)
    }

    fun createMember(request: MemberDto.Request): MemberDto.Response {
        val member: Member = memberRepository.save(Member(request.firstName, request.lastName))

        return MemberDto.Response(member.firstName, member.lastName, member.status)
    }

    fun updateMember(id: Long, request: MemberDto.Request): MemberDto.Response {
        val member: Member? = memberRepository.findByIdOrNull(id)

        member?.let { i ->
            member.updateName(request.firstName, request.lastName)
            return MemberDto.Response(member.firstName, member.lastName, member.status)
        } ?: throw EntityNotFoundException("")
    }

    fun createAuthor(request: AuthorDto.Request): AuthorDto.Response {
        val author: Author = authorRepository.save(Author(request.firstName, request.lastName))

        return AuthorDto.Response(author.firstName, author.lastName)
    }

    fun lendBook(request: LendDto.Request): List<String> {
        val booksApprovedToBurrow: MutableList<String> = mutableListOf()

        val member: Member? = memberRepository.findByIdAndStatus(request.memberId, MemberStatus.ACTIVE)

        member?.let {
            request.bookIds.forEach { i ->
                val book: Book? = bookRepository.findByIdOrNull(i)
                book?.let {
                    val burrowedBook: Lend? = lendRepository.findByBookAndStatus(book, LendStatus.BURROWED)
                    burrowedBook ?: {
                        booksApprovedToBurrow.add(book.name)
                        lendRepository.save(Lend(book, member))
                    }
                } ?: throw EntityNotFoundException("")
            }
        } ?: throw EntityNotFoundException("")

        return booksApprovedToBurrow.toList()
    }

    fun readAuthors(): List<AuthorDto.Response> {
        val authors: List<Author> = authorRepository.findAll()

        return authors.stream().map { i -> AuthorDto.Response(i.firstName, i.lastName) }.toList()
    }

    fun updateBook(bookId: Long, request: BookDto.Request): BookDto.Response {
        val author: Author? = authorRepository.findByIdOrNull(request.authorId)

        author?.let {
            val book: Book? = bookRepository.findByIdOrNull(bookId)
            book?.let {
                book.updateIsbn(request.isbn)
                book.updateName(request.name)
                book.updateAuthor(author)

                return BookDto.Response.of(book)
            } ?: throw EntityNotFoundException("")
        } ?: throw EntityNotFoundException("")
    }

    fun readMembers(): List<MemberDto.Response> {
        val members: List<Member> = memberRepository.findAll()

        return members.stream().map { i -> MemberDto.Response(i.firstName, i.lastName, i.status) }.toList()
    }
}