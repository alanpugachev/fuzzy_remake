package com.alanpugachev.fuzzy_remake.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "username", unique = true)
    var username: String,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "second_name")
    var secondName: String,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
)