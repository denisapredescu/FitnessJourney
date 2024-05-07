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
import com.master.fitnessjourney.entities.TypeExercicesEnum
import com.master.fitnessjourney.helpers.Theme
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
import com.master.fitnessjourney.repository.ExercicesRepository
import org.jetbrains.annotations.Async.Execute

class HomeFragment : Fragment() {

    private lateinit var themePreferences: Theme
    lateinit var themeSwitch: SwitchMaterial
    lateinit var sharedPreferences: SharedPreferences
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

            genre = "Female",
            name = "Exc1",
            time = 30,
            description = "Description",
            difficulty = DifficultyExercicesEnum.MEDIUM,
            type = TypeExercicesEnum.ARMS
        )
         insertExercices(exercice)

        ExercicesRepository.getExcById(3)
        ExercicesRepository.getAllExcByTypeDiffGenre(TypeExercicesEnum.ARMS,DifficultyExercicesEnum.MEDIUM,"Male")

        themeSwitch = view.findViewById<SwitchMaterial>(R.id.themeSwitch)
        themePreferences = Theme(requireContext())
        updateSwitchDrawable(themePreferences.isDarkTheme(), themeSwitch)
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            handleThemeSwitch(themeSwitch, isChecked)
        }
    }
    private fun insertExercices(model: Exercice) {
        ExercicesRepository.insertExercice(model)
        {
            "exercice insert succes".logErrorMessage()
        }
        ExercicesRepository.getAllExc()
    }

    private fun handleThemeSwitch(switch: SwitchMaterial, isChecked: Boolean) {
        themePreferences.saveTheme(isChecked)
        updateSwitchDrawable(isChecked, switch)
    }

    private fun updateSwitchDrawable(isChecked: Boolean, switch: SwitchMaterial) {
        switch.apply {
            this.isChecked = isChecked
//            setButtonDrawable(
//                when (isChecked) {
//                    true -> R.drawable.ic_dark
//                    false -> R.drawable.ic_light
//                }
//            )

        }
    }

}