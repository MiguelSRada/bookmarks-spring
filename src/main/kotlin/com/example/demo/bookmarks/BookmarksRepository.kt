package com.example.demo.bookmarks

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface BookmarksRepository : JpaRepository<Bookmark,Long>{
    fun findBookmarksByCategoryId(categoryId:Int):List<Bookmark>
}