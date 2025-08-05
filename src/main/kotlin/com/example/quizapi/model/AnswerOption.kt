package com.example.quizapi.model


import jakarta.persistence.*

@Entity
data class AnswerOption(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val text: String,

    val isCorrect: Boolean = false, //jel tačan odgovor

    @ManyToOne
    @JoinColumn(name = "song_id")
    val song: Song
)


// @GeneratedValue(strategy = GenerationType.IDENTITY) - Pusti bazu da automatski dodaje jedinstven ID za svaki novi red.