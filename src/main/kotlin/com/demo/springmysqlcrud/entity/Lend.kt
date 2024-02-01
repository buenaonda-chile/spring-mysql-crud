package com.demo.springmysqlcrud.entity

import com.demo.springmysqlcrud.enumeration.LendStatus
import jakarta.persistence.*
import java.time.Instant
import java.time.temporal.ChronoUnit

@Entity
@Table(name = "lend")
class Lend(
    id: Long?,
    status: LendStatus,
    startOn: Instant,
    dueOn: Instant,
    book: Book,
    member: Member
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Column(name = "`status`", nullable = false)
    var status: LendStatus = status
        protected set

    @Column(name = "`start_on`", nullable = false)
    var startOn: Instant = startOn
        protected set

    @Column(name = "`due_on`", nullable = false)
    var dueOn: Instant = dueOn
        protected set

    @JoinColumn(name = "book_id")
    @ManyToOne
    var book: Book = book
        protected set

    @JoinColumn(name = "member_id")
    @ManyToOne
    var member: Member = member
        protected set

    constructor(book: Book, member: Member) : this(
        null,
        LendStatus.BURROWED,
        Instant.now(),
        Instant.now().plus(30, ChronoUnit.DAYS),
        book,
        member
    )
}