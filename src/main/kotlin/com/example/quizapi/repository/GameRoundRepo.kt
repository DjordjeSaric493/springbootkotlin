package com.example.quizapi.repository

import com.example.quizapi.model.GameRound
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRoundRepo : JpaRepository<GameRound,Long>
