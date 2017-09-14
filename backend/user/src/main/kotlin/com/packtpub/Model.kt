package com.packtpub

import javax.persistence.*

@Entity
@Table(name = "packtuser",
    uniqueConstraints =
    arrayOf(UniqueConstraint(columnNames = arrayOf("username"))))
data class PacktUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    val username: String,
    val password: String,
    val active: Boolean = true,

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: List<String> = listOf()
)