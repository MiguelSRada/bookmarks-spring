package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoriesService(private val categoriesRepository: CategoriesRepository) {

    fun getCategories(): List<Category> = categoriesRepository.findAll()

    fun getCategoryById(categoryId: Int): Optional<Category> =
            categoriesRepository.findById(categoryId.toLong())

    fun addCategory(category: Category): Category =
            categoriesRepository.save(category)

    fun putCategory(categoryId: Int, newCategory: Category): Category? =
            categoriesRepository.findById(categoryId.toLong()).map { currentCategory ->
                val updatedCategory: Category =
                        currentCategory
                                .apply {
                                    categoryName = newCategory.categoryName
                                }
                categoriesRepository.save(updatedCategory)
            }.orElse(null)

    fun deleteCategory(categoryId: Long):Boolean =
            categoriesRepository.findById(categoryId).map { category ->
                categoriesRepository.delete(category)
                true
            }.orElse(false)


}
