package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.ExerciceProgress
import com.master.fitnessjourney.entities.MuscleExercicesEnum
import com.master.fitnessjourney.entities.Progress
import com.master.fitnessjourney.entities.TypeExercicesEnum

@Dao
interface ExerciceProgressDao {
    @Insert
    fun insertExcProgress(model: ExerciceProgress)

    @Query("SELECT COUNT(*) FROM exercices_progesses WHERE progressId = :progressId and exerciceId = :exerciceId")
    fun isProgrExc(progressId:Int,exerciceId:Int): Int
}