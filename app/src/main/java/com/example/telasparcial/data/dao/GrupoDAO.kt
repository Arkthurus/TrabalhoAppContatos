package com.example.telasparcial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.telasparcial.data.entities.Grupo

@Dao
interface GrupoDAO {
    @Insert
    suspend fun inserirGrupo(grupo: Grupo)

    @Delete
    suspend fun deletarGrupo(grupo: Grupo)


}