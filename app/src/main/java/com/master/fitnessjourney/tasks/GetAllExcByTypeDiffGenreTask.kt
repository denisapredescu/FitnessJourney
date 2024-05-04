package com.master.fitnessjourney.tasks

import android.os.AsyncTask
import com.master.fitnessjourney.ApplicationController
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.TypeExercicesEnum

class GetAllExcByTypeDiffGenreTask
    (
    val type: TypeExercicesEnum,
    val difficulty: DifficultyExercicesEnum,
    val genre: String,
    val onSuccess: (List<Exercice>) -> Unit
            ): AsyncTask<Unit, Unit, List<Exercice>>() {
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Unit): List<Exercice> {
        return ApplicationController.instance?.appDatabase?.
        exerciceDao?.getAllExcByTypeDiffGenre(type,difficulty,genre) ?: listOf()
        }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: List<Exercice>) {
        super.onPostExecute(result)
        onSuccess.invoke(result)
    }
}