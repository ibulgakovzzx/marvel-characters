package com.example.marver_characters.application.usecase

import com.example.marver_characters.data.model.Character
import com.example.marver_characters.data.network.NetworkConnectionException
import com.example.marver_characters.data.repository.CharactersListRepository
import io.reactivex.Single

class CharactersListInteractor(private val charactersListRepository: CharactersListRepository) {

    fun getCharactersList(query: String) =
            charactersListRepository.getCharacter(query)
                    .onErrorResumeNext {
                        if(it is NetworkConnectionException) {
                            return@onErrorResumeNext charactersListRepository.getCharacterFromDb(query)
                        } else {
                            return@onErrorResumeNext Single.error(it)
                        }
                    }

    fun saveCharacter(character: Character) =
            charactersListRepository.saveCharacter(character)
}