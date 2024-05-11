package com.master.fitnessjourney.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercices")
//data class Exercice(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val genre: String,
//    val name: String,
//    val time: Int,
//    val description: String,
//    val difficulty: DifficultyExercicesEnum,
//    val type: TypeExercicesEnum
//)
//{
//    constructor(genre: String,name: String,time: Int,description: String,difficulty: DifficultyExercicesEnum,type: TypeExercicesEnum)
//            : this(0,genre, name, time, description,difficulty, type)
//}
data class Exercice(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: TypeExercicesEnum,
    val muscle: MuscleExercicesEnum,
    val equipment: String,
    val difficulty: DifficultyExercicesEnum,
    val instructions: String
)
{
    constructor(name: String,type: TypeExercicesEnum,muscle: MuscleExercicesEnum,
        equipment: String,difficulty: DifficultyExercicesEnum,instructions: String)
    :this(0,name, type, muscle, equipment, difficulty, instructions)
}