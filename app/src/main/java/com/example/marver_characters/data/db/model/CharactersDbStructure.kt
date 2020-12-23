package com.example.marver_characters.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marver_characters.data.db.AppDatabase
import com.example.marver_characters.data.db.converter.CharacterConverter
import com.example.marver_characters.data.model.Character
import java.util.*

@Entity(tableName = AppDatabase.DB_TABLE_NAME_CHARACTER)
@TypeConverters(CharacterConverter::class)
class CharactersDbStructure
constructor(
        @PrimaryKey
        var id: Int,
        var name: String,
        var description: String,
        var modified: Date,
        var resourceURI: String,
        var urls: List<String>,
        var thumbnail: String
) {
    fun toModel() = Character(
            id,
            name,
            description,
            modified,
            resourceURI,
            urls,
            thumbnail)
}