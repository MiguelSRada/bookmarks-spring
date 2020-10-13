package com.example.demo.categories

import com.example.demo.bookmarks.Bookmark
import javax.persistence.*

@Entity
@Table(name = "categories")
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name= "categoryName")
        var categoryName: String
){
        @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
        val bookmarks: List<Bookmark> = emptyList()

}