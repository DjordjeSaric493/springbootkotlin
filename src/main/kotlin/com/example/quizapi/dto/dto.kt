package com.example.quizapi.dto

//DTO -data transfer object služi da prenese podatke između slojeva app npr između FE i BE
data class QuestionDTO(
    val songId: Long,
    val coverUrl: String,
    val audioSnippetUrl: String,
    val answerOptions: List<AnswerOptionDTO>
)

data class AnswerOptionDTO(
    val id: Long,
    val text: String
)

data class AnswerResultDTO(
    val correct: Boolean,
    val trivia: String,
    val album: String,
    val artist: String,
    val releaseYear: Int,
    val coverUrl: String
)
