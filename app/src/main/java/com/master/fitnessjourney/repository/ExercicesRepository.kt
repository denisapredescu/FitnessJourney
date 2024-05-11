package com.master.fitnessjourney.repository

import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.MuscleExercicesEnum
import com.master.fitnessjourney.entities.TypeExercicesEnum
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
import com.master.fitnessjourney.tasks.GetAllExcByTypeDiffMuscleTask
import com.master.fitnessjourney.tasks.GetExcByIdTask
import com.master.fitnessjourney.tasks.GetExerciceTask
import com.master.fitnessjourney.tasks.InsertExerciceTask

object ExercicesRepository {//nu e un best practice...
    fun insertExercice(model: Exercice, onSuccess : () -> Unit){
        InsertExerciceTask(onSuccess).execute(model)
    }

    fun getAllExc(){
        GetExerciceTask{exercices ->
            "listSucces: ${exercices.map { it.name }}".logErrorMessage()
        }.execute()
    }
    fun getExcById(id: Int){
        GetExcByIdTask{exercice ->
            if (exercice != null) {
                "exercice: ${exercice.name}".logErrorMessage()
            }
        }.execute(id)
    }

    fun getAllExcByTypeDiffMuscle(type: TypeExercicesEnum, difficulty: DifficultyExercicesEnum, muscle: MuscleExercicesEnum){
        GetAllExcByTypeDiffMuscleTask(type,difficulty,muscle){ exercices ->
            "listSucces: ${exercices.map { it.id }}".logErrorMessage()
        }.execute()
    }
    fun insertAllExercices(list: List<Exercice>){

    }
}
