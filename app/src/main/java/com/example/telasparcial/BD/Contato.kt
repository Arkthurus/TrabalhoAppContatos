package com.example.telasparcial.BD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contatos")
data class Contatos(
    @PrimaryKey(autoGenerate = true)
    val id      : Int,
    val nome    : String,
    val numero  : String
)