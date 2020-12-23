package com.example.marver_characters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marver_characters.data.db.dao.CharacterDao
import com.example.marver_characters.data.db.model.CharactersDbStructure

@Database(
    entities = [CharactersDbStructure::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "app-database"
        const val DB_TABLE_NAME_CHARACTER = "character"
    }


    abstract fun characterDao(): CharacterDao
}