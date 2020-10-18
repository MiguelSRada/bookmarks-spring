package com.example.demo.categories

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Transactional
class CategoriesService(private val categoriesRepository: CategoriesRepository) {

    fun getCategories(categoryName: String?): List<Category> {
        return if (categoryName == null) {
            categoriesRepository.findAll()
        } else categoriesRepository.findByCategoryName(categoryName)
    }

    fun getCategoryById(categoryId: Long): Optional<Category> =
            categoriesRepository.findById(categoryId)

    fun addCategory(category: Category): Category =
            categoriesRepository.findByCategoryName(category.categoryName).firstOrNull()
                ?: categoriesRepository.save(category)

    fun putCategory(categoryId: Long, newCategory: Category): Category? =
            categoriesRepository.findById(categoryId).map { currentCategory ->
                val updatedCategory: Category =
                        currentCategory
                                .apply {
                                    categoryName = newCategory.categoryName
                                }
                categoriesRepository.save(updatedCategory)
            }.orElse(null)

    fun deleteCategory(categoryId: Long): Boolean =
            categoriesRepository.findById(categoryId).map { category ->
                categoriesRepository.delete(category)
                true
            }.orElse(false)
}
