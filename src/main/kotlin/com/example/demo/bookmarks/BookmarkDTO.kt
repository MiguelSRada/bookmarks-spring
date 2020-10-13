package com.example.demo.bookmarks


data class BookmarkDTO(
        val id : Long,
        val name: String,
        val url: String,
        val categoryName: String
)

fun fromEntity(bookmark:Bookmark):BookmarkDTO =
        BookmarkDTO(bookmark.id,bookmark.name,bookmark.url,bookmark.category.categoryName)

