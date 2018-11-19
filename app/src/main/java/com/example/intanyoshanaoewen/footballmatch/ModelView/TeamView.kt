package com.example.intanyoshanaoewen.footballmatch.ModelView

import com.example.intanyoshanaoewen.footballmatch.Model.Team

interface TeamView {
    fun isLoading()
    fun stopLoading()
    fun showTeam(dataHome : List<Team>?, dataAway : List<Team>)
}