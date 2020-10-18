package com.example.demo.bookmarks


data class BookmarkResponse(
        val id : Long,
        val name: String,
        val url: String,
        val categoryName: String
)
data class BookmarkRequest(
        val name: String,
        val url: String,
        val categoryName: String
)

fun fromEntity(bookmark:Bookmark):BookmarkResponse =
        BookmarkResponse(bookmark.id,bookmark.name,bookmark.url,bookmark.category.categoryName)

