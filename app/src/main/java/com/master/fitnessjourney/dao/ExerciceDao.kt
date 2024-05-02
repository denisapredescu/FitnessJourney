package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.master.fitnessjourney.entities.Exercice

@Dao
public interface ExerciceDao {
    @Insert
    fun insertExercice(model: Exercice)

    @Query("DELETE FROM exercices WHERE id = :id")
    fun deleteExerciceById(id: Int)

    @Query("SELECT * FROM exercices")
    fun getAllExc(): List<Exercice>

}