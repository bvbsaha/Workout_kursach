package com.bvbsaha.fitnesskursach.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


open class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    var repository: WorkoutRepository
    var allWorkout: LiveData<List<Workout>>
    var repositoryExercise: ExerciseRepository
    var allExercise: LiveData<List<Exercise>>

    init {
        var workoutDao: WorkoutDao = WorkoutDatabase.getDatabase(application, scope).workoutDao()
        repository = WorkoutRepository(workoutDao)
        allWorkout = repository.allWorkout
        var exerciseDao: ExerciseDao = ExerciseDatabase.getDatabase(application, scope).exerciseDao()
        repositoryExercise = ExerciseRepository(exerciseDao)
        allExercise = repositoryExercise.allExercise
    }

   //Функция для записи тренировок в бд

    fun insert(workout: Workout) = scope.launch(Dispatchers.IO) {
        repository.insert(workout)
    }

    //Функция для записи упражнений в бд

    fun insert(exercise: Exercise) = scope.launch(Dispatchers.IO) {
        repositoryExercise.insert(exercise)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


    fun deleteAll() = scope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repositoryExercise.deleteAll()
    }



    fun deleteById(id: Int) = scope.launch(Dispatchers.IO) {
        repository.deleteById(id)
        repositoryExercise.deleteByWorkoutId(id)
    }



    fun deleteExerciseById(id: Int) = scope.launch(Dispatchers.IO) {
        repositoryExercise.deleteByExerciseId(id)
    }



    fun findWorkoutById(index: Int): Workout {
        allWorkout.value?.forEach { workout ->
            if (workout.id == index)
                return workout
        }
        return Workout(0, "Error", "Error")
    }


    fun findExerciseById(index: Int): Exercise {
        allExercise.value?.forEach { exercise ->
            if (exercise.id == index)
                return exercise
        }
        return Exercise(0, 0, "Error", "Error", "Error", 0, true, 0, "Error", 0, 0, "Error",false)
    }

    fun updateWorkoutTitle(id: Int, title : String) = scope.launch(Dispatchers.IO){
        repository.updateWorkoutTitle(id,title)
    }

    fun updateWorkoutDescription(id: Int, description: String) = scope.launch(Dispatchers.IO){
        repository.updateWorkoutDescription(id,description)
    }

    fun updateExerciseTitle(id: Int, title : String) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseTitle(id,title)
    }

    fun updateExerciseDescription(id: Int, description: String) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseDescription(id,description)
    }

    fun updateExerciseInstruction(id: Int, instruction : String) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseInstruction(id,instruction)
    }

    fun updateExerciseSeries(id: Int, series : Int) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseSeries(id,series)
    }

    fun updateExerciseTime(id: Int, time : Int) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseTime(id,time)
    }

    fun updateExerciseTimeFormat(id: Int, timeFormat : String) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseTimeFormat(id,timeFormat)
    }

    fun updateExerciseRepeat(id: Int, repeat : Int) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExerciseRepeat(id,repeat)
    }

    fun updateExercisePause(id: Int, pause : Int) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExercisePause(id,pause)
    }

    fun updateExercisePauseFormat(id: Int, pauseFormat : String) = scope.launch(Dispatchers.IO){
        repositoryExercise.updateExercisePauseFormat(id,pauseFormat)
    }
    fun updateDone(id: Int,done : Boolean)= scope.launch (Dispatchers.IO){
        repositoryExercise.updateDone(id,done)
    }

    fun changeExerciseID( fromID : Int, toID: Int) = scope.launch(Dispatchers.IO) {
        repositoryExercise.changeExerciseId(200000, fromID)
        if(toID>fromID){
            for(i in fromID until toID){
                repositoryExercise.changeExerciseId(i+1, i)
            }
            repositoryExercise.changeExerciseId(200000, toID)
        }
    }
     fun searchDatabase(searchQuery: String):LiveData<List<Workout>>{
        return repository.searchDatabase(searchQuery)
    }
}