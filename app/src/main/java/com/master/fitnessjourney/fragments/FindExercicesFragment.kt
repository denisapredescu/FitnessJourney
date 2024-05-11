package com.master.fitnessjourney.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.master.fitnessjourney.R
import com.master.fitnessjourney.adapters.ExerciceListAdaptor
import com.master.fitnessjourney.entities.Exercice
import com.master.fitnessjourney.entities.MuscleExercicesEnum
import com.master.fitnessjourney.helpers.VolleyRequestQueue
import com.master.fitnessjourney.helpers.extensions.logErrorMessage
import com.master.fitnessjourney.models.ExerciceModel

class FindExercicesFragment : Fragment() {
    private val items = ArrayList<ExerciceModel>()
    private val adapter = ExerciceListAdaptor(items)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_exercices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val doShowBtn = view.findViewById<Button>(R.id.button_filter)
        doShowBtn.setOnClickListener { doShow() }
        setupRecyclerView()
        getExercices("","","")
    }
    private fun doShow(){
        val selectedTypeEditText = view?.findViewById<TextInputLayout>(R.id.selected_type)?.editText
        val selectedMuscleEditText = view?.findViewById<TextInputLayout>(R.id.selected_muscle)?.editText

        val radioGroupDifficulty = view?.findViewById<RadioGroup>(R.id.radioGroup)
        val checkedRadioButtonId = radioGroupDifficulty?.checkedRadioButtonId
        val selectedType = selectedTypeEditText?.text.toString()
        val selectedMuscle = selectedMuscleEditText?.text.toString()
    items.clear()
        if (checkedRadioButtonId != -1) {
            val checkedButton = checkedRadioButtonId?.let { view?.findViewById<RadioButton>(it) }
            val difficulty = checkedButton?.text.toString()
            getExercices(selectedType,selectedMuscle,difficulty)
        } else {
            getExercices(selectedType,selectedMuscle)
        }

    }
    private fun getExercices(type:String, muscle:String,difficulty:String=""){
        val apiKey="vAeRLb/deis5C2aUbPo++w==dr2qG4CLBi8VsU20"
        var url = "https://api.api-ninjas.com/v1/exercises?type=${type}&muscle=${muscle}&difficulty=${difficulty}"
        val stringRequest = object : StringRequest(
            Method.GET,
            url,
            {
                response -> "success".logErrorMessage()
                val collectionType = object : TypeToken<List<ExerciceModel>>() {}.type
                val responseJsonArray = Gson().fromJson<List<ExerciceModel>>(response, collectionType)

                if(responseJsonArray.isNotEmpty())
                {
                    responseJsonArray.forEach{
                    val model = ExerciceModel(name = it.name, equipment = it.equipment, instructions = it.instructions,type=it.type, difficulty = it.difficulty, muscle = it.muscle)
                    this.items.add(model)
                }}
                adapter.notifyDataSetChanged()

            },
            {
                "That didn't work!".logErrorMessage()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers["X-Api-Key"] = apiKey
                return headers
            }
        }
        VolleyRequestQueue.addToRequestQueue(stringRequest) //for adding the request in queue
        setupRecyclerView()
    }

    fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
//        val exerciceList = listOf(
//            ExerciceModel(name="name1", equipment = "equipment", instructions = "instruction"),
//            ExerciceModel(name="name2", equipment = "equipment", instructions = "instruction"),
//            ExerciceModel(name="name3", equipment = "equipment", instructions = "instruction"),
//            ExerciceModel(name="name4", equipment = "equipment", instructions = "instruction"),
//            ExerciceModel(name="name5", equipment = "equipment", instructions = "instruction"),
//        )
//        val adapter = ExerciceListAdaptor(exerciceList)
        val rvExercices = view?.findViewById<RecyclerView>(R.id.rv_exercices) ?: return
        rvExercices.apply {

            this.layoutManager = layoutManager
            this.adapter = this@FindExercicesFragment.adapter
        }
    }
}