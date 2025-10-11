package com.example.telasparcial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.dao.GrupoContatoDAO
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.data.entities.Grupo
import com.example.telasparcial.data.entities.GrupoContato

@Database(
    entities = [
        Contato::class,
        Grupo::class,
        GrupoContato::class
    ], version = 3
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contatosDao(): ContatosDAO
    abstract fun grupoContatoDao(): GrupoContatoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            val tempINSTANCE = INSTANCE
            if (tempINSTANCE != null) {
                return tempINSTANCE
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration(true)
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

}