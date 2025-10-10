package com.example.telasparcial.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Grupos")
data class Grupo(
    @PrimaryKey val id: Int,
    val nome: String
)