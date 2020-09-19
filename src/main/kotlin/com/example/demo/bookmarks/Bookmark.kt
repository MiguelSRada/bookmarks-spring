package com.example.demo.bookmarks

import org.springframework.context.annotation.Bean


data class Bookmark(val id: Int, var name:String, var url : String, var category: String)