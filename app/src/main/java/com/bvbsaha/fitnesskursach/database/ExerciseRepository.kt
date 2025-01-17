package com.bvbsaha.fitnesskursach.database

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread


class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    var allExercise: LiveData<List<Exercise>> = exerciseDao.getAllExercise()

    //Запись ноовго элемента в бд

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
        allExercise = exerciseDao.getAllExercise()
    }

    //Удаление элемента из бд

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        exerciseDao.deleteAll()
    }

    //удаление по id

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteByWorkoutId(id: Int) {
        exerciseDao.deleteByWorkoutId(id)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteByExerciseId(id: Int) {
        exerciseDao.deleteByExerciseId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseTitle(id: Int, title : String) {
        exerciseDao.updateExerciseTitle(title,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseDescription(id: Int, description : String) {
        exerciseDao.updateExerciseDescription(description,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseInstruction(id: Int, instruction : String) {
        exerciseDao.updateExerciseInstruction(instruction,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseSeries(id: Int, series : Int) {
        exerciseDao.updateExerciseSeries(series,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseTime(id: Int, time : Int) {
        exerciseDao.updateExerciseTime(time,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseTimeFormat(id: Int, timeFormat : String) {
        exerciseDao.updateExerciseTimeFormat(timeFormat,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExerciseRepeat(id: Int, repeat : Int) {
        exerciseDao.updateExerciseRepeat(repeat,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExercisePause(id: Int, pause : Int) {
        exerciseDao.updateExercisePause(pause,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateExercisePauseFormat(id: Int, pauseFormat : String) {
        exerciseDao.updateExercisePauseFormat(pauseFormat,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun changeExerciseId(toID: Int, fromID : Int) {
        exerciseDao.changeExerciseID(toID,fromID)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateDone(id: Int,done:Boolean){
        exerciseDao.updateDone(done,id)
    }
}