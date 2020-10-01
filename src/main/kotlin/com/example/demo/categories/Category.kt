package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        var categoryName: String,

        @OneToMany(mappedBy = "categoryId",fetch = FetchType.LAZY)
        val bookmarks: List<Bookmark>


)