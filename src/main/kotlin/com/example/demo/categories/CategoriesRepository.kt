package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface CategoriesRepository : JpaRepository<Category, Long>{

    fun findByCategoryName(name: String): List<Category>
}