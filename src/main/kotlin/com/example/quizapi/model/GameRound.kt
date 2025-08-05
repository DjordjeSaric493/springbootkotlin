package com.example.quizapi.model

import jakarta.persistence.*

@Entity
data class GameRound(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "song_id")
    val song: Song,

    val isCorrect: Boolean
)
