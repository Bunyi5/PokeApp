package com.example.pokeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {

    abstract val pokemonDatabaseDao: PokemonDatabaseDao

    private class PokemonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val pokemonDatabaseDao = database.pokemonDatabaseDao
                    prePopulateDatabase(pokemonDatabaseDao)
                }
            }
        }

        private suspend fun prePopulateDatabase(pokemonDatabaseDao: PokemonDatabaseDao) {
            val pokemon1 = Pokemon()
            val pokemon2 = Pokemon()
            val pokemon3 = Pokemon()

            pokemon1.pokeName = "charmander"
            pokemon2.pokeName = "ditto"
            pokemon3.pokeName = "bulbasaur"
            Timber.i("Insert is done!")

            pokemonDatabaseDao.insertAll(listOf(pokemon1, pokemon2, pokemon3))
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
                        "pokemon_database")
                        .addCallback(PokemonDatabaseCallback(coroutineScope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}