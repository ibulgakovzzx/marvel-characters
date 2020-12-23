package com.example.marver_characters.data.db.dao

import androidx.room.*
import com.example.marver_characters.data.db.converter.CharacterConverter
import com.example.marver_characters.data.db.model.CharactersDbStructure
import io.reactivex.Completable
import io.reactivex.Single

@Dao
@TypeConverters(CharacterConverter::class)
abstract class CharacterDao {

    @Query("SELECT * FROM character WHERE name=:name")
    abstract fun getList(name: String): Single<List<CharactersDbStructure>>

    @Query("SELECT * FROM character WHERE id=:id")
    abstract fun get(id: Int): Single<CharactersDbStructure>

    @Query("SELECT * FROM character WHERE id=:id")
    abstract fun getSync(id: Int): CharactersDbStructure?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(dbStructure: CharactersDbStructure): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(dbStructure: CharactersDbStructure): Int

    fun save(dbStructure: CharactersDbStructure) = Completable.fromAction {
        val item = getSync(dbStructure.id)
        if(item == null) {
            insert(dbStructure)
        } else {
            update(dbStructure)
        }
    }

}