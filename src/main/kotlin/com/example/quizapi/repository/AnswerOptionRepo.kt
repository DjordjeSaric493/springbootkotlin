package com.example.quizapi.repository

import com.example.quizapi.model.AnswerOption
import com.example.quizapi.model.Song
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface  AnswerOptionRepo: JpaRepository<AnswerOption,Long> {//opcija tipa long
fun findBySong(song: Song): List<AnswerOption> //nađi odg po pesmi SELECT * FROM answer_option WHERE song_id = :song.id

}
