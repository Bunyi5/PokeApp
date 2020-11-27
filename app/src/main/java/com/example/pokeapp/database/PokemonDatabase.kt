package com.example.pokeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pokeapp.network.PokeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val pokemonDatabaseDao: PokemonDatabaseDao

    private class PokemonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            createDatabase()
        }

        private fun createDatabase() {
            INSTANCE?.let { database ->
                scope.launch {
                    withContext(Dispatchers.IO) {
                        insertIntoDatabase(database.pokemonDatabaseDao)
                    }
                }
            }
        }

        private suspend fun insertIntoDatabase(pokemonDatabaseDao: PokemonDatabaseDao) {
            try {
                pokemonDatabaseDao.insertAll(PokeApi.retrofitService.getPokemons().results)
                Timber.i("Success: Pokemons received and inserted in the database")
            } catch (e: Exception) {
                Timber.i("Failure: ${e.message}")
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): PokemonDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java,
                        "pokemon_database"
                    )
                        .addCallback(PokemonDatabaseCallback(coroutineScope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}