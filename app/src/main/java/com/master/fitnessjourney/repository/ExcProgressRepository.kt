package com.master.fitnessjourney.repository

import com.master.fitnessjourney.entities.ExerciceProgress
import com.master.fitnessjourney.tasks.GetIdByNameTask
import com.master.fitnessjourney.tasks.GetIdByUserDateTask
import com.master.fitnessjourney.tasks.InsertExcProgressTask
import com.master.fitnessjourney.tasks.InsertExerciceTask
import com.master.fitnessjourney.tasks.IsProgrExcTask
import com.master.fitnessjourney.tasks.IsProgressSetTodayUserLoggedTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ExcProgressRepository {
    var id1:Int=0;
    var id2:Int=0;
    fun insertExercProgress(model: ExerciceProgress, onSuccess : () -> Unit){
        InsertExcProgressTask(onSuccess).execute(model)
    }

    fun uu(name:String,username:String){

//        GetIdByNameTask(name){
//        id1=it
//        }.execute()

        val resExc = GetIdByNameTask(name){}.execute().get()
        val idExc = resExc ?: 0

        val resProgr = GetIdByUserDateTask(username){}.execute().get()
        val idProgr = resProgr ?: 0
//        GetIdByUserDateTask(username){
//        id2=it
//        }.execute()
        val excProgress = ExerciceProgress(
            progressId = idProgr,
            exerciceId = idExc,
            status = false
        )
        if(excProgress.progressId!=0 && excProgress.exerciceId!=0)
        {
            isProgressExc(excProgress.progressId,excProgress.exerciceId){}
        }

    }

    fun isProgressExc(progressId: Int, exerciceId: Int, onSuccess: () -> Unit){
        IsProgrExcTask(progressId,exerciceId){response ->
            if (response == false)
            {
                val excProgress = ExerciceProgress(
                    progressId = progressId,
                    exerciceId = exerciceId,
                    status = false
                )
                insertExercProgress(excProgress){}
            }

            //"exercice is: $response".logErrorMessage()

        }.execute()
    }
}

