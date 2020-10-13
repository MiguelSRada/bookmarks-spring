package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import com.example.demo.bookmarks.BookmarkDTO

class CategoryDTO(
        val id: Long,
        val categoryName: String
)

fun fromEntity(category: Category): CategoryDTO =
        CategoryDTO(category.id,category.categoryName)
