package com.example.quizapi.repository

import com.example.quizapi.model.Song
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface  SongRepo: JpaRepository<Song,Long> { //jpa repo sadrži tipa findall(),findbyid(),save() itd itb

    @Query("SELECT s FROM Song s ORDER BY function('RANDOM')")
    fun findRandomSong(): Song //Function 'findRandomSong' must have a body.
}