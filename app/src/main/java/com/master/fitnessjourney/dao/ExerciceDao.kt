package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.TypeExercicesEnum

@Dao
public interface ExerciceDao {
    @Insert
    fun insertExercice(model: Exercice)

    @Query("DELETE FROM exercices WHERE id = :id")
    fun deleteExerciceById(id: Int)

    @Query("SELECT * FROM exercices")
    fun getAllExc(): List<Exercice>

    @Query("SELECT * FROM exercices WHERE type = :type AND difficulty = :difficulty AND genre = :genre")
    fun getAllExcByTypeDiffGenre(type: TypeExercicesEnum, difficulty: DifficultyExercicesEnum, genre: String): List<Exercice>

    @Query("SELECT * FROM exercices WHERE id = :id")
    fun getExcById(id: Int): Exercice?

}