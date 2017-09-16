package com.packtpub
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
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

fun com.packtpub.PacktUser.toUserDetails(): UserDetails =
    User.withUsername(username)
        .password(password)
        .accountExpired(!active)
        .accountLocked(!active)
        .credentialsExpired(!active)
        .disabled(!active)
        .authorities(roles.map(::SimpleGrantedAuthority).toList())
        .build()