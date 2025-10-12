package com.example.telasparcial.di

import android.content.Context
import androidx.room.Room
import com.example.telasparcial.data.AppDataBase
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.dao.GrupoContatoDAO
import com.example.telasparcial.data.dao.GrupoDAO
import com.example.telasparcial.data.entities.Grupo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context, applicationScope: CoroutineScope
    ): AppDataBase {
        val db = Room.databaseBuilder(
            context = context.applicationContext,
            klass = AppDataBase::class.java,
            name = "app_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()

        applicationScope.launch {
            db.grupoDao().buscarPeloNome("Favoritos")
                ?: db.grupoDao().inserirGrupo(Grupo(nome = "Favoritos"))
        }

        return db
    }

    @Provides
    fun provideContatosDao(appDatabase: AppDataBase): ContatosDAO {
        return appDatabase.contatosDao()
    }

    @Provides
    fun provideGrupoDao(appDatabase: AppDataBase): GrupoDAO {
        return appDatabase.grupoDao()
    }

    @Provides
    fun provideGrupoContatoDao(appDatabase: AppDataBase): GrupoContatoDAO {
        return appDatabase.grupoContatoDao()
    }
}