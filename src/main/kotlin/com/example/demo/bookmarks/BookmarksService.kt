package com.example.demo.bookmarks

import com.example.demo.categories.Category
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookmarksService(private val bookmarksRepository: BookmarksRepository) {

    fun getBookmarks(): List<Bookmark> = bookmarksRepository.findAll()

    fun getBookmarkById(bookmarkId: Int): Optional<Bookmark> =
            bookmarksRepository.findById(bookmarkId.toLong())

    fun getBookmarkByCategoryId(categoryId: Int): List<Bookmark> =
            bookmarksRepository.findBookmarksByCategoryId(categoryId)

    fun addBookmark(bookmark: Bookmark): Bookmark =
            bookmarksRepository.save(bookmark)

    fun putBookmark(bookmarkId: Int, newName: String?, newUrl: String?, newCategory: Category?): Bookmark? =
            bookmarksRepository.findById(bookmarkId.toLong()).map { currentBookmark ->
                val updateBookmark: Bookmark =
                        currentBookmark
                                .apply {
                                    newName?.let { name = newName }
                                    newUrl?.let { url = newUrl }
                                    newCategory?.let { category = newCategory }
                                }
                bookmarksRepository.save(updateBookmark)
            }.orElse(null)

    fun deleteBookmark(bookmarkId: Long): Boolean =
            bookmarksRepository.findById(bookmarkId).map { bookmark ->
                bookmarksRepository.delete(bookmark)
                true
            }.orElse(false)


}