package com.bvbsaha.fitnesskursach.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.*
import com.bvbsaha.fitnesskursach.R
import com.bvbsaha.fitnesskursach.creator.RepeatActivity
import com.bvbsaha.fitnesskursach.creator.TimeActivity
import com.bvbsaha.fitnesskursach.workout.CreateWorkoutActivity
import kotlinx.android.synthetic.main.activity_exercise.*


/**
 * class ExerciseActivity is create new Exercise
 * @property radioGroup is group with two RadioButtons. First is Time. Second is Repeat. User choose button according from exercise
 * @property title is EditText where user put title new exercise
 * @property description is EditText where user put description new exercise
 * @property instruction is EditText where user put instruction new exercise
 * @property TITLE is address title new exercise where new intent put data. And where next activity can find data from this activity
 * @property DESCRIPTION is address description new exercise where new intent put data. And where next activity can find data from this activity
 * @property INSTRUCTION is address instruction new exercise where new intent put data. And where next activity can find data from this activity
 */

class CreateExerciseActivity : AppCompatActivity() {

    lateinit var radioGroup: RadioGroup //Кнопки для выбора варианта выполнения упражнений
    lateinit var title: EditText        //Ввод названия нового упражнения
    lateinit var description: EditText  //Ввод сложности упражнения
    lateinit var instruction: EditText  //Ввод инструкции к выполнению упражнения
    private lateinit var spinner: Spinner
    /**
     * it finds layout elements and stores to variable
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        radioGroup = findViewById(R.id.radio_group)
        title = findViewById(R.id.exercise_title_edit_text)
        //description = findViewById(R.id.exercise_description_edit_text)
        spinner = findViewById(R.id.diffspinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.difficult,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        supportActionBar?.hide()
        instruction = findViewById(R.id.exercise_instruction_edit_text)
    }

    /**
     * It checks EditText is empty. If yes show statement. But if EditText is not empty. It create new Intent, put data and start new activity
     */

    fun nextExercise(view: View) {
        if (!(title.text.toString() == "")) {
            val nextIntent: Intent = if (radioGroup.checkedRadioButtonId == R.id.radio_button_repeat) {
                Intent(this, RepeatActivity::class.java)
            } else {
                Intent(this, TimeActivity::class.java)
            }
            nextIntent.putExtra(TITLE, title.text.toString())
            nextIntent.putExtra(INSTRUCTION, instruction.text.toString())
            nextIntent.putExtra(DESCRIPTION, spinner.selectedItem.toString())
            nextIntent.putExtra(CreateWorkoutActivity.ID, intent.getIntExtra(CreateWorkoutActivity.ID, 0))
            startActivity(nextIntent)
            finish()
        } else if (title.text.toString().length > 30 || description.text.toString().length > 50) {
            val toast =
                Toast.makeText(applicationContext, "Insert shorter description or title please", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val toast =
                Toast.makeText(applicationContext, "Insert data please", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    /**
     * It shows dialog with message "Are you sure you want to exit?"
     * This dialog have two options
     * YES retreat
     * NO do nothing
     */

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Create Workout")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("YES") { dialog, which ->
                super.onBackPressed()
            }
            .setNegativeButton("NO", null)
            .show()
    }

    companion object {
        const val TITLE: String = "com.bvbsaha.fitnesskurasch.etitle"
        const val DESCRIPTION: String = "com.bvbsaha.fitnesskurasch.description"
        const val INSTRUCTION: String = "com.bvbsaha.fitnesskurasch.instrucuton"
    }

}