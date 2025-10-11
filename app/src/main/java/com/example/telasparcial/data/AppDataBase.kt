package com.example.telasparcial.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.dao.GrupoContatoDAO
import com.example.telasparcial.data.dao.GrupoDAO
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.data.entities.Grupo
import com.example.telasparcial.data.entities.GrupoContato
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Contato::class,
        Grupo::class,
        GrupoContato::class
    ], version = 4
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contatosDao(): ContatosDAO
    abstract fun grupoContatoDao(): GrupoContatoDAO
    abstract fun grupoDao(): GrupoDAO

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

                    // Cria o grupo "Favoritos"
                    CoroutineScope(Dispatchers.IO).launch {
                        instance.grupoDao().inserirGrupo(Grupo(nome = "Favoritos"))
                    }

                    return instance
                }
            }
        }
    }

}