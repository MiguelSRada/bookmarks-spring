package com.example.demo.bookmarks

import com.example.demo.categories.CategoriesService
import com.example.demo.categories.Category
import com.example.demo.categories.CategoryController
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

val listBookmarks: List<BookmarkResponse> = listOf(
        BookmarkResponse(1,"google", "http://google.com","searchEngine"),
        BookmarkResponse(2,"facebook", "http://facebook.com","socialMedia")

)

@ExtendWith(SpringExtension::class, MockitoExtension::class)
internal class BookmarkControllerTest {

    @Mock
    private lateinit var bookmarksService: BookmarksService

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(BookmarkController(bookmarksService)).build()
    }

    @Test
    fun getBookmarks() {
        given(bookmarksService.getBookmarks()).willReturn(listBookmarks)

        mockMvc.perform(get("/bookmarks"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("google"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].url").value("http://google.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].categoryName").value("searchEngine"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].name").value("facebook"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].url").value("http://facebook.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].categoryName").value("socialMedia"))
    }

    @Test
    fun getBookmarkById() {
        given(bookmarksService.getBookmarkById(1))
                .willReturn(Optional.of(Bookmark(1,"google", "http://google.com", Category(1,"searchEngine"))))

        mockMvc.perform(get("/bookmarks/1"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("google"))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value("http://google.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("categoryName").value("searchEngine"))
    }

    @Test
    fun getBookmarkByCategoryId() {
        given(bookmarksService.getBookmarkByCategoryId(1))
                .willReturn(listOf(BookmarkResponse(2,"facebook", "http://facebook.com","socialMedia")))

        mockMvc.perform(get("/bookmarks/categories/1"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("facebook"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].url").value("http://facebook.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].categoryName").value("socialMedia"))
    }

    @Test
    fun addBookmark() {
    }

    @Test
    fun deleteBookmark() {
    }

    @Test
    fun updateBookmark() {
    }
}