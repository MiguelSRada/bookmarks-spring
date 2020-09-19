package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import org.springframework.stereotype.Component
import java.awt.print.Book

@Component
class CategoriesService(private val categories: MutableList<Category> = mutableListOf()) {

    fun getCategories() = categories

    fun findCategoryById(id:Int) = categories.firstOrNull{ it.id == id }

    fun updateCategory(id: Int, categoryName: String): Category? {
        val category = categories.firstOrNull { it.id == id }
        return if (category == null) category
        else {
            category.categoryName = categoryName
            category
        }
    }

    fun deleteCategory(id: Int) = categories.removeIf { it.id == id }

    fun createCategory(category: Category): Category? {
        categories.firstOrNull { it.categoryName == category.categoryName }?.let { return null }
        val categoryWithMaxId = categories.maxBy { it.id }
        val newId = if (categoryWithMaxId == null) 1 else categoryWithMaxId!!.id + 1
        categories.add(Category(newId,category.categoryName))
        return categories.last()
    }
}
