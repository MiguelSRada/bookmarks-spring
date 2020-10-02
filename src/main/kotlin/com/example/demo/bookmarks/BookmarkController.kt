package com.example.demo.bookmarks

import com.example.demo.categories.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/bookmarks")
class BookmarkController(private val bookmarksService: BookmarksService) {

    @GetMapping
    fun getBookmarks(): ResponseEntity<List<Bookmark>> =
            ResponseEntity.ok(bookmarksService.getBookmarks())

    @GetMapping("/{id}")
    fun getBookmarkById(@PathVariable("id") id: Int): ResponseEntity<Bookmark> =
            bookmarksService.getBookmarkById(id)
                    .map { category -> ResponseEntity.ok(category) }
                    .orElse(ResponseEntity.notFound().build())

    @GetMapping("/categories/{categoryId}")
    fun getBookmarkByCategoryId(@PathVariable("categoryId") categoryId: Int): ResponseEntity<List<Bookmark>> =
            ResponseEntity.ok(bookmarksService.getBookmarkByCategoryId(categoryId))

    @PostMapping
    fun addBookmark(@RequestBody bookmark: Bookmark): ResponseEntity<Bookmark> =
            ResponseEntity.ok(bookmarksService.addBookmark(bookmark))

    @DeleteMapping("/{id}")
    fun deleteBookmark(@PathVariable("id") id: Long): ResponseEntity<Void> =
            if (bookmarksService.deleteBookmark(id)) ResponseEntity(HttpStatus.ACCEPTED)
            else ResponseEntity.notFound().build()


    @PutMapping("/{bookmarkId}")
    fun updateBookmark(
            @PathVariable bookmarkId: Int,
            @RequestParam newName: String?,
            @RequestParam newUrl: String?,
            @RequestParam newCategory: Category?): ResponseEntity<Bookmark> =
            if (bookmarksService.putBookmark(bookmarkId, newName, newUrl, newCategory) == null) ResponseEntity.notFound().build()
            else ResponseEntity.ok(bookmarksService.putBookmark(bookmarkId, newName, newUrl, newCategory)!!)


}