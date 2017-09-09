package com.packtpub

import javax.persistence.*

@Entity
@Table(name = "packtuser")
data class PacktUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    val username: String,
    val password: String,
    val active: Boolean = true,

    @ElementCollection
    val roles: List<String> = listOf()
)