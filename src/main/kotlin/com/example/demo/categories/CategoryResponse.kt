package com.example.demo.categories

class CategoryResponse(
        val id: Long,
        val categoryName: String
)


fun fromEntity(category: Category?): CategoryResponse? {
    return if (category!= null) CategoryResponse(category.id,category.categoryName)
    else null

}

