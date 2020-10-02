package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(private val categoriesService : CategoriesService) {

    @GetMapping
    fun getCategories(): ResponseEntity<List<Category>> =
            ResponseEntity.ok(categoriesService.getCategories())

    @GetMapping("/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: Int):ResponseEntity<Category> =
            categoriesService.getCategoryById(categoryId).map { category ->
                ResponseEntity.ok(category)
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long):ResponseEntity<Void> =
            if (categoriesService.deleteCategory(categoryId)) ResponseEntity(HttpStatus.ACCEPTED)
            else ResponseEntity.notFound().build()


    @PostMapping
    fun createCategory(@RequestBody category:Category): ResponseEntity<Category> =
            ResponseEntity.ok(categoriesService.addCategory(category))

    @PutMapping("/{categoryId}")
    fun updateCategory(@PathVariable categoryId:Int, @RequestParam categoryName: String) =
            categoriesService.putCategory(categoryId, Category(categoryId,categoryName))
}