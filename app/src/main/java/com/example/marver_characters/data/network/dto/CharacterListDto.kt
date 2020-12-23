package com.example.marver_characters.data.network.dto

class CharacterListDto(
    var available: Int,
    var returned: Int,
    var collectionURL: String,
    var items: List<CharacterSummaryDto>)