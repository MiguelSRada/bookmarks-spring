package com.example.demo.categories

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(private val categoriesService : CategoriesService) {

    @GetMapping
    fun getCategories() = categoriesService.getCategories()

    @GetMapping("/{id}")
    fun findCategoryById(@PathVariable id: Int) = categoriesService.findCategoryById(id)

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int) = categoriesService.deleteCategory(id)

    @PostMapping
    fun createCategory(@RequestBody category:Category) = categoriesService.createCategory(category)

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id:Int, @RequestParam categoryName: String) =
            categoriesService.updateCategory(id, categoryName)
}