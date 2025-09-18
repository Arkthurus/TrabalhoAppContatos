package com.example.telasparcial.BD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contatos")
data class Contatos(
    val nome   : String,
    @PrimaryKey(autoGenerate = true)
    val numero : String
)