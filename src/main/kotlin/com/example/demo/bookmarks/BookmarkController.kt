package com.example.demo.bookmarks

import com.example.demo.categories.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/bookmarks")
class BookmarkController(private val bookmarksService: BookmarksService) {

    @GetMapping
    fun getBookmarks(): List<BookmarkDTO> =
            bookmarksService.getBookmarks().map { fromEntity(it) }

    @GetMapping("/{id}")
    fun getBookmarkById(@PathVariable("id") id: Long): ResponseEntity<BookmarkDTO> =
            bookmarksService.getBookmarkById(id)
                    .map { bookmark -> ResponseEntity.ok((fromEntity(bookmark))) }
                    .orElse(ResponseEntity.notFound().build())

    @GetMapping("/categories/{categoryId}")
    fun getBookmarkByCategoryId(@PathVariable("categoryId") categoryId: Long): List<BookmarkDTO> =
            bookmarksService.getBookmarkByCategoryId(categoryId).map { fromEntity(it) }

    @PostMapping
    fun addBookmark(@RequestBody bookmark: Bookmark): BookmarkDTO =
            fromEntity(bookmarksService.addBookmark(bookmark))

    @DeleteMapping("/{id}")
    fun deleteBookmark(@PathVariable("id") id: Long): ResponseEntity<Void> =
            if (bookmarksService.deleteBookmark(id)) ResponseEntity(HttpStatus.ACCEPTED)
            else ResponseEntity.notFound().build()


    @PutMapping("/{bookmarkId}")
    fun updateBookmark(
            @PathVariable bookmarkId: Long,
            @RequestParam newName: String?,
            @RequestParam newUrl: String?,
            @RequestParam newCategory: Category?): ResponseEntity<Bookmark> =
            if (bookmarksService.putBookmark(bookmarkId, newName, newUrl, newCategory) == null) ResponseEntity.notFound().build()
            else ResponseEntity.ok(bookmarksService.putBookmark(bookmarkId, newName, newUrl, newCategory)!!)


}