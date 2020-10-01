package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class CategoriesService(private val categoriesRepository: CategoriesRepository) {

    fun getCategories(): List<Category> = categoriesRepository.findAll()

    fun getCategoryById(categoryId: Long): ResponseEntity<Category> =
            categoriesRepository.findById(categoryId).map { category ->
                ResponseEntity.ok(category)
            }.orElse(ResponseEntity.notFound().build())

    fun addCategory(category: Category): ResponseEntity<Category> =
            ResponseEntity.ok(categoriesRepository.save(category))

    fun putCategory(categoryId: Long, newCategory: Category): ResponseEntity<Category> =
            categoriesRepository.findById(categoryId).map { currentCategory ->
                val updatedCategory: Category =
                        currentCategory
                                .copy(
                                        categoryName = newCategory.categoryName
                                )
                ResponseEntity.ok().body(categoriesRepository.save(updatedCategory))
            }.orElse(ResponseEntity.notFound().build())

    fun deleteCategory(categoryId: Long): ResponseEntity<Void> =
            categoriesRepository.findById(categoryId).map { category ->
                categoriesRepository.delete(category)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())


}
