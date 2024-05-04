package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.Progress
import com.master.fitnessjourney.models.ProgressWithExercices

@Dao
public interface ProgressDao {

    @Insert
    fun insertProgress(model: Progress)

   // @Query("UPDATE progresses SET status= 1 WHERE id = :id")
    //fun updateProgressStatus(id: Int): Progress?

    @Query("DELETE FROM progresses WHERE id = :id")
    fun deleteProgress(id: Int)

//    @Query("SELECT exercices.id, progresses.id,exercices.name FROM exercices " +
//            "join exercices_progesses on(exercices.id == exercices_progesses.exerciceId)" +
//            "join progresses on(exercices_progesses.progressId == progresses.id)" +
//            " WHERE status = 0 ")
//    fun getAllExercicesInProgress(): List<Exercice>


}