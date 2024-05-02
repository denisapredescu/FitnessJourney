package com.master.fitnessjourney.repository

import com.master.fitnessjourney.AppDatabase
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
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
    fun insertAllExercices(list: List<Exercice>){

    }
}