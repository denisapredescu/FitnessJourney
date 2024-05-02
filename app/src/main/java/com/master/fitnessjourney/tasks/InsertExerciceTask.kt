package com.master.fitnessjourney.tasks

import android.os.AsyncTask
import com.master.fitnessjourney.ApplicationController
import com.master.fitnessjourney.entities.Exercice

class InsertExerciceTask(
    val onSuccess: () -> Unit
): AsyncTask<Exercice,Unit,Unit>() {
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Exercice) {
        params.getOrNull(0)?.let { exercice ->
            ApplicationController
                .instance?.appDatabase?.exerciceDao?.insertExercice(exercice)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Unit) {
        super.onPostExecute(result)
        onSuccess.invoke()
    }
}