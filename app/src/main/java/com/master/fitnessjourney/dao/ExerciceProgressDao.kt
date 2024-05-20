package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.ExerciceProgress
import com.master.fitnessjourney.entities.MuscleExercicesEnum
import com.master.fitnessjourney.entities.Progress
import com.master.fitnessjourney.entities.TypeExercicesEnum
import com.master.fitnessjourney.models.ExerciceInProgress

@Dao
interface ExerciceProgressDao {
    @Insert
    fun insertExcProgress(model: ExerciceProgress)

    @Query("SELECT COUNT(*) FROM exercices_progesses WHERE progressId = :progressId and exerciceId = :exerciceId")
    fun isProgrExc(progressId:Int,exerciceId:Int): Int

    @Query("SELECT exercices.id 'exerciceId', progresses.id 'progressId',exercices.name FROM exercices " +
            "join exercices_progesses on(exercices.id == exercices_progesses.exerciceId)" +
            "join progresses on(exercices_progesses.progressId == progresses.id)" +
            " WHERE status = 0 AND username = :username")
    fun getAllExercicesInProgress(username: String): List<ExerciceInProgress>

    @Delete
    fun deleteExcProgress(model: ExerciceProgress)

    @Update
    fun updateExcProgress(model: ExerciceProgress)

}