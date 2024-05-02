package com.master.fitnessjourney.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.master.fitnessjourney.R
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.TypeExercicesEnum
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
import com.master.fitnessjourney.repository.ExercicesRepository

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exercice = Exercice(
            id = 1,
            genre = "Female",
            name = "Exc1",
            time = 30,
            description = "Description",
            difficulty = DifficultyExercicesEnum.MEDIUM,
            type = TypeExercicesEnum.ARMS
        )
         insertExercices(exercice)
    }
    private fun insertExercices(model: Exercice) {
        ExercicesRepository.insertExercice(model)
        {
            "exercice insert succes".logErrorMessage()
        }
        ExercicesRepository.getAllExc()
    }
}