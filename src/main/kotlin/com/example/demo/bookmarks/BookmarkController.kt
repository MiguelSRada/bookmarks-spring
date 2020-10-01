package com.example.demo.bookmarks

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/bookmarks")
class BookmarkController(private  val bookmarksService: BookmarksService) {

    @GetMapping
    fun getBookmarks(): List<Bookmark> =
            bookmarksService.getBookmarks()

    @GetMapping("/{id}")
    fun getBookmarkById(@PathVariable("id") id: Long): ResponseEntity<Bookmark> =
            bookmarksService.getBookmarkById(id)

    @GetMapping("/categories/{categoryId}")
    fun getBookmarkByCategoryId(@PathVariable("categoryId") categoryId: Long): List<Bookmark>  =
            bookmarksService.getBookmarkByCategoryId(categoryId)

    @PostMapping
    fun addBookmark(@RequestBody bookmark: Bookmark): ResponseEntity<Bookmark> =
            bookmarksService.addBookmark(bookmark)

    @DeleteMapping("/{id}")
    fun deleteBookmark(@PathVariable("id") id: Long): ResponseEntity<Void> =
            bookmarksService.deleteBookmark(id)

    @PutMapping("/{bookmarkId}")
    fun updateBookmark(
            @PathVariable bookmarkId: Long?,
            @RequestParam name: String?,
            @RequestParam url: String?,
            @RequestParam categoryId: Long?): ResponseEntity<Bookmark> =
            bookmarksService.putBookmark(bookmarkId!!, Bookmark(bookmarkId,name!!, url!!,categoryId!!))
}