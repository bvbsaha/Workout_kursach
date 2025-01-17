package com.bvbsaha.fitnesskursach.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.bvbsaha.fitnesskursach.domain.PlayerModel
import javax.inject.Inject

class LeaderBoardFirebaseRepoImpl @Inject constructor() : LeaderBoardRepo {

    private var myRef: DatabaseReference? = null
    private val _leaderBoardPlayer = MutableLiveData<List<PlayerModel>>()
    private val leaderBoardPlayer: LiveData<List<PlayerModel>> = _leaderBoardPlayer
    private val leaderBoardDbChild = "SportChallenge"

    init {
        val database =
            Firebase.database("https://fitness-c14c8-default-rtdb.firebaseio.com/")
        myRef = database.getReference("leaderboard")

        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listOfPlayer = mutableListOf<PlayerModel>()
                for (player in dataSnapshot.child(leaderBoardDbChild).children) {
                    listOfPlayer.add(player.getValue(PlayerModel::class.java)!!)
                }
                listOfPlayer.sortByDescending { player -> player.score.toInt() }
                _leaderBoardPlayer.value = listOfPlayer
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    override fun addScoreToLeaderBoardList(player: PlayerModel) {
        myRef?.child(leaderBoardDbChild)?.push()?.setValue(player)
    }

    override fun getLeaderBoardList(): LiveData<List<PlayerModel>> {
        return leaderBoardPlayer
    }

}