package com.example.marver_characters.data.network.dto

import com.example.marver_characters.data.model.Character
import java.util.*


class CharacterDto
constructor(
    var id: Int,
    var name: String,
    var description: String,
    var modified: Date,
    var resourceURI: String,
    var urls: List<UrlDto>,
    var thumbnail: ImageInfoDto)
{
    fun toModel(): Character =
        Character(
            id,
            name,
            description,
            modified,
            resourceURI,
            urls.map { it.url },
            thumbnail.getMediumPath())
}