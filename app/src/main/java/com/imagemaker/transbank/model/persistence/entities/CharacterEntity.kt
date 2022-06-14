package com.imagemaker.transbank.model.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "json_character") val jsonCharacter: String,
)
