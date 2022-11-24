package com.bvbsaha.fitnesskursach.creator

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.bvbsaha.fitnesskursach.R
import com.bvbsaha.fitnesskursach.database.Exercise
import com.bvbsaha.fitnesskursach.menu.MainActivity


/**
 * RepeatActivity is creator repeat exercise
 * @property series is EditText where user put series number new exercise
 * @property repeat is EditText where user put repeat number new exercise
 * @property pause is EditText where user put pause number new exercise
 * @property spinner is EditText where user choose pause formats new exercise
 * @author Mateusz Karłowski
 */

class RepeatActivity : AppCompatActivity() {

    lateinit var series: EditText
    lateinit var repeat: EditText
    lateinit var pause: EditText
    private lateinit var spinner: Spinner

    /**
     * It finds layouts elements and stores to variable and set array strings to spinner
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeat)
        series = findViewById(R.id.series_repeat)
        repeat = findViewById(R.id.repeat)
        pause = findViewById(R.id.pause_repeat)
        spinner = findViewById(R.id.pauseMenuRepeat)
        ArrayAdapter.createFromResource(
            this,
            R.array.time_formats,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        supportActionBar?.hide()
    }

    /**
     * check EditText is not empty, evokes add and create new intent and start it
     */

    fun addExerciseRepeat(view: View) {
        if (series.text.toString() == "" || repeat.text.toString() == "" || pause.text.toString() == "") {
            val toast = Toast.makeText(applicationContext, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT)
            toast.show()
        }
            else if(series.text.toString().toInt() < 1 || repeat.text.toString().toInt() < 1 || pause.text.toString().toInt() < 1){
                val toast = Toast.makeText(applicationContext,"Проверьте введенные данные",Toast.LENGTH_SHORT)
                toast.show()
        }
         else if (series.text.toString().toInt() > 99 ||
            repeat.text.toString().toInt() > 10000 ||
            (pause.text.toString().toInt() > 6000 && spinner.selectedItem == "seconds") ||
            (pause.text.toString().toInt() > 99 && spinner.selectedItem == "minutes")
        ) {
            val toast = Toast.makeText(applicationContext, "Пожалуйста, ведите меньшие значения", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            add()
            var nextIntent: Intent = Intent(this, ExerciseActivity::class.java)
            nextIntent.putExtra(SetTitleActivity.ID, intent.getIntExtra(SetTitleActivity.ID, 0))
            startActivity(nextIntent)
            finish()
        }
    }

    /**
     * check EditText is not empty, evokes add and finish activity
     */

    fun finishRepeat(view: View) {
        if (series.text.toString() == "" || repeat.text.toString() == "" || pause.text.toString() == "") {
            val toast = Toast.makeText(applicationContext, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT)
            toast.show()
        } else if(series.text.toString().toInt() < 1 || repeat.text.toString().toInt() < 1 || pause.text.toString().toInt() < 1){
            val toast = Toast.makeText(applicationContext,"Проверьте введенные данные",Toast.LENGTH_SHORT)
            toast.show()
        }
        else if (series.text.toString().toInt() > 99 ||
            repeat.text.toString().toInt() > 10000 ||
            (pause.text.toString().toInt() > 6000 && spinner.selectedItem == "seconds") ||
            (pause.text.toString().toInt() > 99 && spinner.selectedItem == "minutes")
        ) {
            val toast = Toast.makeText(applicationContext, "Пожалуйста, фвывведите меньшие значения", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            add()
            finish()
        }
    }

    /**
     * It adds new Exercise to database for this get data from intent and EditText
     */

    private fun add() {
        var id: Int = 0
        MainActivity.workoutViewModel.allExercise.observe(this, Observer { words ->
            words?.let { id = it.size }
        })
        MainActivity.workoutViewModel.insert(
            Exercise(
                id
                , intent.getIntExtra(SetTitleActivity.ID, 0),
                intent.getStringExtra(ExerciseActivity.TITLE).toString(),
                intent.getStringExtra(ExerciseActivity.DESCRIPTION).toString(),
                intent.getStringExtra(ExerciseActivity.INSTRUCTION).toString(),
                series.text.toString().toInt(),
                false,
                0,
                "",
                repeat.text.toString().toInt(),
                pause.text.toString().toInt(),
                spinner.selectedItem.toString()
            )
        )
    }

    /**
     * It shows dialog window with message "Are you sure you want to not save this exercise?"
     * It has 2 options:
     * YES back to MenuActivity
     * NO do nothing
     */

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Create Exercise")
            .setMessage("Are you sure you want to not save this exercise?")
            .setPositiveButton("YES") { dialog, which ->
                super.onBackPressed()
            }
            .setNegativeButton("NO", null)
            .show()
    }
}