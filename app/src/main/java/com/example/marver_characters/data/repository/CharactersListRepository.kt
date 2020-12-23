package com.example.marver_characters.data.repository

import com.example.marver_characters.data.db.dao.CharacterDao
import com.example.marver_characters.data.model.Character
import com.example.marver_characters.data.network.Api
import com.example.marver_characters.data.network.util.AuthHelper
import io.reactivex.Completable
import io.reactivex.Single

class CharactersListRepository
constructor(private val api: Api,
            private val characterDao: CharacterDao,
            private val authHelper: AuthHelper) {

    fun getCharacter(query: String): Single<List<Character>> = api.getCharactersList(
            authHelper.publicKey,
            authHelper.md5Key,
            authHelper.timestamp,
            100,
            query)
            .map { dto ->
                dto.data.results.map { it.toModel() }
            }

    fun getCharacterFromDb(query: String): Single<List<Character>> =
            characterDao.getList(query)
                    .map { list ->
                        list.map { it.toModel() }
                    }

    fun saveCharacter(character: Character): Completable =
            characterDao.save(character.toDbStructure())
}