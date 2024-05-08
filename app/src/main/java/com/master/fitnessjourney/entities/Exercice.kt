package com.master.fitnessjourney.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercices")
data class Exercice(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val genre: String,
    val name: String,
    val time: Int,
    val description: String,
    val difficulty: DifficultyExercicesEnum,
    val type: TypeExercicesEnum
)
{
    constructor(genre: String,name: String,time: Int,description: String,difficulty: DifficultyExercicesEnum,type: TypeExercicesEnum)
            : this(0,genre, name, time, description,difficulty, type)
}