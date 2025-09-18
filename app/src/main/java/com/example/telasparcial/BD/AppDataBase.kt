package com.example.telasparcial.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contatos::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contatosDao(): ContatosDAO

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
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

}