package com.alanpugachev.fuzzy_remake.repository

import com.alanpugachev.fuzzy_remake.entity.Result
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResultRepository : JpaRepository<Result, Long> {
    fun findResultById(id: Long): Result?

    fun save(result: Result): Result
}