package com.example.quizapi.service

import com.example.quizapi.dto.AnswerOptionDTO
import com.example.quizapi.dto.QuestionDTO
import com.example.quizapi.model.AnswerOption
import com.example.quizapi.model.GameRound
import com.example.quizapi.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.*


@Service  //CAN'T SPELL CLASS WITHOUT "ASS"
class GameService( //primarni konstruktor za DI
    private val songRepo: SongRepo,  //private val jer zna koje beamove da mune u kod
    private val answerOptionRepo: AnswerOptionRepo,
    private val userRepo: UserRepo,
    private val gameRoundRepo: GameRoundRepo
){
    fun getNextQuestion(userId: Long) : QuestionDTO{
        val song  = songRepo.findRandomSong()
        val options= answerOptionRepo.findBySong(song)

        return QuestionDTO(
            songId = song.id,
            coverUrl =  song.coverUrl,
            audioSnippetUrl = song.audioSnippetUrl,
            answerOptions= options.map { AnswerOptionDTO(it.id,it.text) }
        )
    }
    @Transactional
    fun submitAnswer(userId: Long, songId: Long, selectedOptionId: Long): Boolean {
        val user = userRepo.findById(userId).orElseThrow()
        val song = songRepo.findById(songId).orElseThrow()
        val selectedOption = answerOptionRepo.findById(selectedOptionId).orElseThrow()

        val isCorrect = selectedOption.isCorrect

        if (isCorrect) {
            user.score += 1
        } else {
            user.score -= 0.5.toInt() // score je Int, zaokruženo
        }

        userRepo.save(user)

        val round = GameRound(
            user = user,
            song = song,
            isCorrect = isCorrect
        )

        gameRoundRepo.save(round)
        return isCorrect
    }

    fun getTriviaForSong(songId: Long): String {
        val song = songRepo.findById(songId).orElseThrow()
        return song.trivia
    }
}


