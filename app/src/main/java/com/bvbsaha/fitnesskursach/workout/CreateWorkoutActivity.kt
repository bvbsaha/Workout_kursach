package com.bvbsaha.fitnesskursach.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.bvbsaha.fitnesskursach.R
import com.bvbsaha.fitnesskursach.exercise.CreateExerciseActivity
import com.bvbsaha.fitnesskursach.database.Workout
import com.bvbsaha.fitnesskursach.menu.MainActivity

//
//Cоздание новых тренирвок

class CreateWorkoutActivity : AppCompatActivity() {

    lateinit var titleEditText: EditText//название
    lateinit var descriptionEditText: EditText//описание


    //поиск эдементов и сохранение в переменные
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_title)
        titleEditText = findViewById(R.id.title_edit_text_set_activity)
        descriptionEditText = findViewById(R.id.desription_edit_text_set_activity)
        supportActionBar?.hide()
    }

    //проверка
    //создание и заполенение интентов

    fun next(view: View) {
        if (titleEditText.text.toString() == "" || descriptionEditText.text.toString() == "") {
            val toast = Toast.makeText(applicationContext, "Поажлуйста, введите данные", Toast.LENGTH_SHORT)
            toast.show()
        } else if (titleEditText.text.toString().length > 30 || descriptionEditText.text.toString().length > 50) {
            val toast =
                Toast.makeText(applicationContext, "Пожалуйста, введите меньшие данные", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            //присваивание id тренировке (кол-во элементов списка +1)
            val id = MainActivity.workoutViewModel.allWorkout.value!!.size + 1
            val workout = Workout(id, titleEditText.text.toString(), descriptionEditText.text.toString())
            MainActivity.workoutViewModel.insert(workout)
            var nextIntent = Intent(this, CreateExerciseActivity::class.java)
            nextIntent.putExtra(ID, id)
            startActivity(nextIntent)
            finish()
        }
    }

    companion object {
        const val ID: String = "com.bvbsaha.fitnesskursach.currentId"
    }



    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Создание тренировки")
            .setMessage("Вы уверены, что хотите выйти?")
            .setPositiveButton("ДА") { dialog, which ->
                super.onBackPressed()
            }
            .setNegativeButton("НЕТ", null)
            .show()
    }
}