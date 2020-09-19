package com.example.demo.bookmarks

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException



@RestController
@RequestMapping("/bookmarks")
class BookmarkController(private  val bookmarksService: BookmarksService) {

    @GetMapping
    fun getBookmarks(): List<Bookmark> = bookmarksService.getBookmarks().toList()

    @GetMapping("/{id}")
    fun findBookmarkById(@PathVariable("id") id: Int): Bookmark = bookmarksService.findBookmarkById(id)

    @GetMapping("/categories/{category}")
    fun findBookmarksByCategory(@PathVariable("category") category: String): List<Bookmark> =
            bookmarksService.findBookmarksByCategory(category)

    @PostMapping
    fun addBookmark(@RequestBody bookmark: Bookmark): Boolean = bookmarksService.addBookmark(bookmark)

    @DeleteMapping("/{id}")
    fun deleteBookmarkById(@PathVariable("id") id: Int): Boolean = bookmarksService.deleteBookmarkById(id)

    @PutMapping("/{id}")
    fun updateBookmark(
            @PathVariable id: Int?,
            @RequestParam name: String?,
            @RequestParam url: String?,
            @RequestParam category: String?): Bookmark {
        if ((id == null) ) throw ResponseStatusException(HttpStatus.NOT_FOUND, "there isn't id")
        if ((name == null) and (url == null) and (category == null) ) throw ResponseStatusException(HttpStatus.NOT_FOUND, "nothing to update")
        return bookmarksService.updateBookmark(id, name, url,category )
    }
}