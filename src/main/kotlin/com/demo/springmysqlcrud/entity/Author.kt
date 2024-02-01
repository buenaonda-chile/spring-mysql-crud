package com.demo.springmysqlcrud.entity

import jakarta.persistence.*

@Entity
@Table(name = "author")
class Author (
    id: Long?,
    firstName: String,
    lastName: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = id
        protected set

    @Column(name = "`first_name`", nullable = false)
    var firstName: String = firstName
        protected set

    @Column(name = "`last_name`", nullable = false)
    var lastName: String = lastName
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "author")
    protected val mutableBooks: MutableList<Book> = mutableListOf()
    val books: List<Book> get() = mutableBooks.toList()

    constructor(firstName: String, lastName: String): this(null, firstName, lastName)
}