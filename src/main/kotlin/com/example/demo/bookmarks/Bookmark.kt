package com.example.demo.bookmarks

import com.example.demo.categories.Category
import javax.persistence.*

@Entity
@Table(name = "bookmarks")
class Bookmark(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name:String,
        var url : String,
        @ManyToOne
        @JoinColumn(name = "categoryId")
        var category : Category
)


