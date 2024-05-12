package com.master.fitnessjourney.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.master.fitnessjourney.R
import com.master.fitnessjourney.entities.DifficultyExercicesEnum
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.MuscleExercicesEnum
import com.master.fitnessjourney.entities.TypeExercicesEnum
import com.master.fitnessjourney.helpers.Theme
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
import com.master.fitnessjourney.models.ExerciceModel
import com.master.fitnessjourney.repository.ExercicesRepository
import org.jetbrains.annotations.Async.Execute

class HomeFragment : Fragment() {

    private lateinit var themePreferences: Theme
    lateinit var themeSwitch: SwitchMaterial
    lateinit var sharedPreferences: SharedPreferences
//    lateinit var isChecked: Boolean

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

        name = "Exc2",
        type = TypeExercicesEnum.CARDIO,
        muscle = MuscleExercicesEnum.NECK,
        equipment = "Equipment2",
        difficulty = DifficultyExercicesEnum.EXPERT,
        instructions = "Instruction2"
        )
         //insertExercices(exercice)

        ExercicesRepository.getExcById(1)
        ExercicesRepository.getAllExcByTypeDiffMuscle(TypeExercicesEnum.CARDIO,DifficultyExercicesEnum.EXPERT,MuscleExercicesEnum.LATS)

        themeSwitch = view.findViewById<SwitchMaterial>(R.id.themeSwitch)
        themePreferences = Theme(requireContext())
        themeSwitch.isChecked = themePreferences.isDarkTheme()
        loadSwitchTheme()
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            handleThemeSwitch(isChecked)
        }
    }
    private fun insertExercices(model: ExerciceModel) {
        ExercicesRepository.insertExercice(model)
        {
            "exercice insert succes".logErrorMessage()
        }
        ExercicesRepository.getAllExc()
    }

    private fun handleThemeSwitch(isChecked: Boolean) {
        themePreferences.saveTheme(isChecked)
        loadSwitchTheme()
    }

    private fun loadSwitchTheme() {
        themePreferences.loadTheme()
    }

}