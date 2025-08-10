package com.example.quizapi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Song(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String = "",
    val artist: String = "",
    val album: String = "",
    val releaseYear: Int = 0,
    val coverUrl: String = "",
    val audioSnippetUrl: String = "",

    @Column(length = 1000)
    val trivia: String = ""
)
