package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.Progress
import com.master.fitnessjourney.models.ExerciceByDateModel
import com.master.fitnessjourney.models.ExerciceInProgress
import com.master.fitnessjourney.models.ProgressWithExercices
import java.util.Date

@Dao
public interface ProgressDao {

    @Insert
    fun insertProgress(model: Progress)

    @Query("UPDATE exercices_progesses SET status= 1 WHERE exerciceId = :exerciceId AND progressId = :progressId")
    fun updateProgressStatus(exerciceId: Int, progressId: Int)

    @Query("DELETE FROM progresses WHERE id = :id")
    fun deleteProgress(id: Int)

    @Query("SELECT exercices.id 'exerciceId', progresses.id 'progressId',exercices.name FROM exercices " +
            "join exercices_progesses on(exercices.id == exercices_progesses.exerciceId)" +
            "join progresses on(exercices_progesses.progressId == progresses.id)" +
            " WHERE status = 0 ")
    fun getAllExercicesInProgress(): List<ExerciceInProgress>

    @Query("SELECT exercices.id 'exerciceId', name FROM exercices " +
            "JOIN exercices_progesses ON (exercices.id == exercices_progesses.exerciceId)" +
            "JOIN progresses ON (progresses.id == exercices_progesses.progressId)" +
            "WHERE status = 1 AND date = :chooseDate")
    fun getDoneExercicesByDate(chooseDate: Date): List<ExerciceByDateModel>

}