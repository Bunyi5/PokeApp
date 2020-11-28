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

    private enum class Mode {
        CREATE, OPEN
    }

    private class PokemonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            editDatabase(Mode.CREATE)
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            editDatabase(Mode.OPEN)
        }

        private fun editDatabase(mode: Mode) {
            INSTANCE?.let { database ->
                scope.launch {
                    withContext(Dispatchers.IO) {
                        insertOrUpdateDatabase(database.pokemonDatabaseDao, mode)
                    }
                }
            }
        }

        private suspend fun insertOrUpdateDatabase(pokemonDatabaseDao: PokemonDatabaseDao, mode: Mode) {
            try {
                val pokemonList: List<Pokemon> = PokeApi.retrofitService.getPokemons().results
                pokemonList.forEach {
                    setPokemonApiId(it)
                }

                when (mode) {
                    Mode.CREATE -> {
                        pokemonDatabaseDao.insertAll(pokemonList)
                        Timber.i("Success: Pokemons received and inserted in the database")
                    }
                    Mode.OPEN -> {
                        pokemonList.forEach {
                            pokemonDatabaseDao.update(it.apiId, it.pokeName, it.pokeUrl)
                        }
                        Timber.i("Success: Pokemons received and updated in the database")
                    }
                }
            } catch (e: Exception) {
                Timber.i("Failure: ${e.message}")
            }
        }

        private fun setPokemonApiId(pokemon: Pokemon) {
            val pokeUrl = pokemon.pokeUrl
            pokemon.apiId = pokeUrl.substring(34, pokeUrl.length - 1).toLong()
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