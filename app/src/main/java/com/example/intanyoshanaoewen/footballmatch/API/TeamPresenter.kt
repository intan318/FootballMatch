package com.example.intanyoshanaoewen.footballmatch.API

import com.example.intanyoshanaoewen.footballmatch.Model.TeamResponse
import com.example.intanyoshanaoewen.footballmatch.ModelView.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(
        private val view: TeamView,
        private val apirepository: APIrepository,
        private val gson: Gson
){
    fun getDetailTeamList(teamHome: String?, teamAway: String?){
        view.isLoading()

        async(UI){
            val dataHome = bg {
                gson.fromJson(
                        apirepository
                                .doRequest(FootballAPI.getTeam(teamHome)),
                        TeamResponse::class.java
                )
            }
            val dataAway = bg {
                gson.fromJson(
                        apirepository
                                .doRequest(FootballAPI.getTeam(teamAway)),
                        TeamResponse::class.java
                )
            }
            view.showTeam(dataHome.await().teams, dataAway.await().teams)
            view.stopLoading()
        }
    }
}