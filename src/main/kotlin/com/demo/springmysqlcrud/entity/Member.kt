package com.demo.springmysqlcrud.entity

import com.demo.springmysqlcrud.enumeration.LendStatus
import com.demo.springmysqlcrud.enumeration.MemberStatus
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member (
    id: Long?,
    firstName: String,
    lastName: String,
    status: MemberStatus
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

    @Column(name = "`status`", nullable = false)
    var status: MemberStatus = status
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "member")
    protected val mutableLends: MutableList<Lend> = mutableListOf()
    val lends: List<Lend> get() = mutableLends.toList()

    constructor(firstName: String, lastName: String): this(null, firstName, lastName, MemberStatus.ACTIVE)

    fun updateName(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }
}