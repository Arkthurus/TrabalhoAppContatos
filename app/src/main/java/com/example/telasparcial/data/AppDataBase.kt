package com.example.telasparcial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.dao.GrupoContatoDAO
import com.example.telasparcial.data.dao.GrupoDAO
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.data.entities.Grupo
import com.example.telasparcial.data.entities.GrupoContato

@Database(entities = [Contato::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contatosDAO(): ContatosDAO

    abstract fun grupoDAO(): GrupoDAO

    abstract fun gruposContatosDAO(): GrupoContatoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}
