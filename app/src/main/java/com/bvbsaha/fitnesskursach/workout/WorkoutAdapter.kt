package com.bvbsaha.fitnesskursach.workout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bvbsaha.fitnesskursach.R
import com.bvbsaha.fitnesskursach.database.Workout
import com.bvbsaha.fitnesskursach.workout.WorkoutViewActivity

/**
 * class WorkoutAdapter extends from RecycleView.Adapter and it is for RecycleView
 *  @property mWorkout is list Workout
 *  @see Workout
 *  @property id stores id clicked item
 *  @property ID is string which stores id address. This address is for intent can put ID. This property is companion object because ViewActivity must have address for read data
 */

class WorkoutAdapter(context: Context) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {
    private var mInflater = LayoutInflater.from(context)
    private lateinit var mWorkout: List<Workout>
    private lateinit var cardView: CardView
    var id: Int = 0


    /**
     * onCreateViewHolder find CardView in layout and return items look like R.layout.workout_item with data
     */

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WorkoutViewHolder {
        val itemView = mInflater.inflate(R.layout.workout_item, parent, false)
        cardView = itemView.findViewById(R.id.cardView)
        return WorkoutViewHolder(itemView, this)

    }

    /**
     * onBindViewHolder set data from mWorkout to item
     */

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WorkoutViewHolder, positon: Int) {
        var currentStinrg = mWorkout[positon].title
        holder.id = mWorkout[positon].id
        holder.mWorkoutTitle.text = currentStinrg
        currentStinrg = mWorkout[positon].description
        holder.mWorkoutDescription.text = currentStinrg



    }

    /**
     * @return how many item
     */

    override fun getItemCount(): Int {
        return if (::mWorkout.isInitialized) {
            mWorkout.size
        } else {
            0
        }
    }

    /**
     * class WorkoutViewHolder set onClick card listener and find layout item in R.layout.workout_item
     * @property id is workout id
     * @property mWorkoutTitle is TextView with workout Title, Its id is R.id.workout_title
     * @property mWorkoutDescription is TextView with workout Description. Its id is R.id.workout_description
     * @constructor sets OnClick as OnClickListener
     */

    inner class WorkoutViewHolder(viewItem: View, workoutAdapter: WorkoutAdapter) : RecyclerView.ViewHolder(viewItem),
        View.OnClickListener {
        var mAdapter: WorkoutAdapter = workoutAdapter
        var id: Int = this@WorkoutAdapter.id
        var mWorkoutTitle: TextView = viewItem.findViewById(R.id.workout_title)
        var mWorkoutDescription: TextView = viewItem.findViewById(R.id.workout_description)

        init {
            viewItem.setOnClickListener(this)
        }

        /**
         * onClick starts new Intent and put ID to this Intent
         */

        override fun onClick(view: View) {
            var intentView = Intent(itemView.context, WorkoutViewActivity::class.java)
            intentView.putExtra(ID, id)
            itemView.context.startActivity(intentView)

        }


    }

    /**
     * function setList set new List with new Data
     *  @property list is list with workout
     */

    fun setList(list: List<Workout>) {
        mWorkout = list
        this.notifyDataSetChanged()
    }

    companion object {
        var ID: String = "com.bvbsaha.fitnesskursach.menu.id"
    }
}