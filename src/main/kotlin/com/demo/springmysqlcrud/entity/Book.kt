package com.demo.springmysqlcrud.entity

import jakarta.persistence.*

@Entity
@Table(name = "book")
class Book(
    id: Long?,
    name: String,
    isbn: String,
    author: Author
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = id
        protected set

    @Column(name = "`name`", nullable = false)
    var name: String = name
        protected set

    @Column(name = "`isbn`", nullable = false)
    var isbn: String = isbn
        protected set

    @JoinColumn(name = "author_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var author: Author = author
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "book")
    protected val mutableLends: MutableList<Lend> = mutableListOf()
    val lends: List<Lend> get() = mutableLends.toList()

    constructor(name: String, isbn: String, author: Author): this(null, name, isbn, author)

    fun updateIsbn(isbn: String) {
        this.isbn = isbn
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun updateAuthor(author: Author) {
        this.author = author
    }
}