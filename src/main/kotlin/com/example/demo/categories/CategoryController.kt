package com.example.demo.categories

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(private val categoriesService: CategoriesService) {

    @GetMapping
    fun getCategories(@RequestParam categoryName: String?): List<CategoryResponse?> =
            categoriesService.getCategories(categoryName).map { fromEntity(it) }

    @GetMapping("/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: Long): ResponseEntity<Category> =
            categoriesService.getCategoryById(categoryId).map { category ->
                ResponseEntity.ok(category)
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<Void> =
            if (categoriesService.deleteCategory(categoryId)) ResponseEntity(HttpStatus.ACCEPTED)
            else ResponseEntity.notFound().build()

    @PostMapping
    fun createCategory(@RequestBody category: Category): CategoryResponse? =
            fromEntity(categoriesService.addCategory(category))

    @PutMapping("/{categoryId}")
    fun updateCategory(@PathVariable categoryId: Long, @RequestParam categoryName: String) =
            fromEntity(categoriesService.putCategory(categoryId, Category(categoryId, categoryName)))
}