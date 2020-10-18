package com.example.demo.categories

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

val listCategories: List<Category> = listOf(
        Category(1,"WebMail"),
        Category(2,"Social Media")
)

@ExtendWith(SpringExtension::class, MockitoExtension::class)
internal class CategoryControllerTest {

    @Mock
    private lateinit var categoryService: CategoriesService

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(CategoryController(categoryService)).build()
    }

    @Test
    fun getAllCategories(){
        given(categoryService.getCategories(null)).willReturn(listCategories)
        mockMvc.perform(get("/categories"))
                .andExpect( status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].categoryName").value("WebMail"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].categoryName").value("Social Media"))
    }

    @Test
    fun getCategoryByNameWhenExist(){
        given(categoryService.getCategories("WebMail"))
                .willReturn(listOf(Category(1,"WebMail")))

        mockMvc.perform(get("/categories?categoryName=WebMail"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].categoryName").value("WebMail"))
    }

    @Test
    fun getCategoryByNameWhenDoesntExist(){
        given(categoryService.getCategories("News"))
                .willReturn(listOf())

        mockMvc.perform(get("/categories?categoryName=News"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.content().json("[]"))
    }

    @Test
    fun getCategoryById(){
        given(categoryService.getCategoryById(2))
                .willReturn(Optional.of(Category(2,"WebMail")))

        mockMvc.perform(get("/categories/2"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("categoryName").value("WebMail"))
    }

    @Test
    fun deleteCategoryByIdWhenExist(){
        given(categoryService.deleteCategory(2)).willReturn(true)

        mockMvc.perform(delete("/categories/2"))
                .andExpect(status().isAccepted)
    }
    @Test
    fun deleteCategoryByIdWhenDoesntExist() {
        given(categoryService.deleteCategory(4)).willReturn(false)

        mockMvc.perform(delete("/categories/4"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun createBookmark(){
        given(categoryService.addCategory(Category(1,"WebMail")))
                .willReturn(Category(1,"WebMail"))

        mockMvc.perform(post("/categories/"))
                .andExpect(status().isOk)//por que responde not found???
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("categoryName").value("WebMail"))
    }

    @Test
    fun updateBookmark(){
        `when`(categoryService.putCategory(1, Category(1,"Sports")))
                .thenReturn(Category(1,"Sports"))

        mockMvc.perform(put("/categories/1?categoryName=Sports"))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("categoryName").value("Sports"))
    }
}