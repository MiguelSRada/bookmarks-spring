package com.example.demo.categories

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(private val categoriesService : CategoriesService) {

    @GetMapping
    fun getCategories() = categoriesService.getCategories()

    @GetMapping("/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: Long) = categoriesService.getCategoryById(categoryId)

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long) = categoriesService.deleteCategory(categoryId)

    @PostMapping
    fun createCategory(@RequestBody category:Category) = categoriesService.addCategory(category)

    @PutMapping("/{categoryId}")
    fun updateCategory(@PathVariable categoryId:Long, @RequestParam categoryName: String) =
            categoriesService.putCategory(categoryId, Category(categoryId,categoryName))
}