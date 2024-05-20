package com.master.fitnessjourney.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.master.fitnessjourney.R
import com.master.fitnessjourney.adapters.ExerciceDoneAdaptor
import com.master.fitnessjourney.adapters.ExerciceListAdaptor
import com.master.fitnessjourney.models.ExerciceModel
import com.master.fitnessjourney.repository.ExcProgressRepository
import com.master.fitnessjourney.repository.ExercicesRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class StatisticsFragment : Fragment() {
    var calendar = Calendar.getInstance()
    private val items = ArrayList<ExerciceModel>()
    private val adapter = ExerciceDoneAdaptor(items)
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPreferences =
            (activity?.getSharedPreferences("CONTEXT_DETAILS", Context.MODE_PRIVATE))!!
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val username = sharedPreferences.getString("email",null)
        if (username != null) {
            ExercicesRepository.getExcDoneByDateUsername("20","05","2024",username) { exercices ->
                items.clear()
                items.addAll(exercices)
                adapter.notifyDataSetChanged()
            }
        }

        val button = view?.findViewById<Button>(R.id.button_select_date)

        val setDate = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(viewDate: DatePicker?, year: Int, month: Int, day: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                DateUpdate()

                val dataText = view?.findViewById<TextView>(R.id.tv_date_choosed)?.text
                if(dataText!=null && username != null) {
                    val parts = dataText.split("/")
                    val day_str = parts[0]
                    val month_str = parts[1]
                    val year_str = parts[2]

                    ExercicesRepository.getExcDoneByDateUsername(day_str,month_str,year_str,username) { exercices ->
                        items.clear()
                        items.addAll(exercices)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        adapter.notifyDataSetChanged()

        button?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                setDate,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun DateUpdate() {
        val customFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(customFormat, Locale.getDefault())
        val textView = view?.findViewById<TextView>(R.id.tv_date_choosed)
        if (textView != null) {
            textView.text = sdf.format(calendar.time)
        }

    }
    fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        val rvExercices = view?.findViewById<RecyclerView>(R.id.rv_exercices_done) ?: return
        rvExercices.apply {

            this.layoutManager = layoutManager
            this.adapter = this@StatisticsFragment.adapter
        }
    }
}