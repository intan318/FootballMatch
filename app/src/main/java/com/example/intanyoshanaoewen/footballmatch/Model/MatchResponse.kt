package com.example.intanyoshanaoewen.footballmatch.Model

import com.google.gson.annotations.SerializedName

data  class MatchResponse(
        @SerializedName("events") //buat list
        val events: List<Match>? = null,

        @SerializedName("event") //buat search
        val event: List<Match>? = null,

        @SerializedName("teams")
        val teams: List<Team>? = null
)
