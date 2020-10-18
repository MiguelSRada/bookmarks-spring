package com.example.demo.bookmarks

import com.example.demo.categories.CategoriesRepository
import com.example.demo.categories.CategoriesService
import com.example.demo.categories.Category
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Transactional
class BookmarksService(private val bookmarksRepository: BookmarksRepository) {

    fun getBookmarks(): List<BookmarkResponse> =
            bookmarksRepository.findAll().map { fromEntity(it) }

    fun getBookmarkById(bookmarkId: Long): Optional<Bookmark> =
            bookmarksRepository.findById(bookmarkId)

    fun getBookmarkByCategoryId(categoryId: Long): List<BookmarkResponse> =
            bookmarksRepository.findBookmarksByCategoryId(categoryId).map { fromEntity(it) }

    fun addBookmark(bookmark: Bookmark): Bookmark =
            bookmarksRepository.save(bookmark)

    fun putBookmark(bookmarkId: Long, newName: String?, newUrl: String?, newCategory: Category?): Bookmark? =
            bookmarksRepository.findById(bookmarkId).map { currentBookmark ->
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