package com.example.marver_characters.data.model

import com.example.marver_characters.data.db.model.CharactersDbStructure
import java.io.Serializable
import java.util.*

data class Character
constructor(
    var id: Int,
    var name: String,
    var description: String,
    var modified: Date,
    var resourceURI: String,
    var urls: List<String>,
    var thumbnail: String
): Serializable
{
    fun toDbStructure() = CharactersDbStructure(
            id,
            name,
            description,
            modified,
            resourceURI,
            urls,
            thumbnail)
}