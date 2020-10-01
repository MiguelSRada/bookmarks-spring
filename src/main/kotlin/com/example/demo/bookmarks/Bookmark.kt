package com.example.demo.bookmarks

import javax.persistence.*

@Entity
@Table(name = "bookmarks")
data class Bookmark(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name:String,
        val url : String,
        @ManyToOne
        val categoryId: Int
)


