package com.example.demo.bookmarks

import jdk.nashorn.internal.objects.NativeArray.indexOf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookmarksService(private val bookmarks: MutableList<Bookmark> = mutableListOf()) {

    fun getBookmarks() = bookmarks

    fun findBookmarkById(id: Int) = bookmarks.first { it.id == id }

    fun findBookmarksByCategory(category: String) = bookmarks.filter { it.category == category }

    fun deleteBookmarkById(id: Int) = bookmarks.removeIf { it.id == id }

    fun addBookmark(bookmark: Bookmark): Boolean {
        bookmarks.firstOrNull { it.name == bookmark.name }?.let { return false }
        val bookmarkWithMaxId = bookmarks.maxBy { it.id }
        val newId = if (bookmarkWithMaxId == null) 1 else bookmarkWithMaxId!!.id + 1
        bookmarks.add(Bookmark(newId,bookmark.name,bookmark.url,bookmark.category))
        return true
    }

    fun updateBookmark(id: Int, name: String? = null, url: String? = null, category: String? = null): Bookmark {
        bookmarks.firstOrNull { it.id == id }?.let {
            if (name != null) it.name = name
            if (url != null) it.url = url
            if (category != null) it.category = category
        }
        return bookmarks.first { it.id == id }

    }
}