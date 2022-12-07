package com.bvbsaha.fitnesskursach.menu

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bvbsaha.fitnesskursach.R
import com.bvbsaha.fitnesskursach.database.Exercise
import com.bvbsaha.fitnesskursach.database.Workout
import com.bvbsaha.fitnesskursach.database.WorkoutViewModel

/**
 * MainActivity is splash screen
 * It shows  on 0.7 seconds layout activity_welcom and switch to MenuActivity
 */


class MainActivity : AppCompatActivity() {

    /**
     * onCreate create WorkoutViewModel and ready other data in database.
     * The second task onCreate is stop splash screen on 7 seconds
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom)
        supportActionBar?.hide()
        workoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
        Handler().postDelayed({
            val homeIntent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(homeIntent)
            MainActivity.workoutViewModel.insert(Workout(80,"Руки","Сложно"))
            MainActivity.workoutViewModel.insert(Exercise(80,80,"Dsgsggsgs","Ssggcs","sgddsdgdsg",4,true,13,"seconds",0,5,"seconds"))
            finish()
        }, 700)
    }

    companion object {
        lateinit var workoutViewModel: WorkoutViewModel
    }
}
