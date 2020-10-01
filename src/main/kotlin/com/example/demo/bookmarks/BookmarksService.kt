package com.example.demo.bookmarks

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class BookmarksService(private val bookmarksRepository: BookmarksRepository) {

    fun getBookmarks(): List<Bookmark> = bookmarksRepository.findAll()

    fun getBookmarkById(bookmarkId: Long): ResponseEntity<Bookmark> =
            bookmarksRepository.findById(bookmarkId).map { bookmark ->
                ResponseEntity.ok(bookmark)
            }.orElse(ResponseEntity.notFound().build())

    fun getBookmarkByCategoryId(categoryId: Long): List<Bookmark> =
            bookmarksRepository.findBookmarksByCategoryId(categoryId.toInt())


    fun addBookmark(bookmark: Bookmark): ResponseEntity<Bookmark> =
            ResponseEntity.ok(bookmarksRepository.save(bookmark))

    fun putBookmark(bookmarkId: Long, newBookmark: Bookmark): ResponseEntity<Bookmark> =
            bookmarksRepository.findById(bookmarkId).map { currentBookmark ->
                val updateBookmark: Bookmark =
                        currentBookmark
                                .copy(
                                        name = newBookmark.name,
                                        url = newBookmark.url,
                                        categoryId = newBookmark.categoryId
                                )
                ResponseEntity.ok().body(bookmarksRepository.save(updateBookmark))
            }.orElse(ResponseEntity.notFound().build())

    fun deleteBookmark(bookmarkId: Long): ResponseEntity<Void> =
            bookmarksRepository.findById(bookmarkId).map { bookmark ->
                bookmarksRepository.delete(bookmark)
                ResponseEntity<Void>(HttpStatus.ACCEPTED)
            }.orElse(ResponseEntity.notFound().build())


}