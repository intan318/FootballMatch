package com.example.intanyoshanaoewen.footballmatch.ModelView

import com.example.intanyoshanaoewen.footballmatch.Model.Match

interface MatchView {
    fun isLoading()
    fun stopLoading()
    fun showMatch(data : List<Match>?)
}