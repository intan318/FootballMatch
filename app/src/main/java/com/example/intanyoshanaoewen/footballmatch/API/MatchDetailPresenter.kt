package com.example.intanyoshanaoewen.footballmatch.API

import com.example.intanyoshanaoewen.footballmatch.Model.MatchResponse
import com.example.intanyoshanaoewen.footballmatch.ModelView.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailPresenter(
        private val view: MatchView,
        private val apirepository: APIrepository,
        private val gson: Gson
  ){
    fun getDetailMatch(matchId: String?){
        view.isLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apirepository
                        .doRequest(FootballAPI.getMatch(matchId)),
                        MatchResponse::class.java)
            }
            view.showMatch(data.await().events)
            view.stopLoading()
        }
    }
}