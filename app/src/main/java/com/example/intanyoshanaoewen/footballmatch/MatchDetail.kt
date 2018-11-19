package com.example.intanyoshanaoewen.footballmatch

import android.icu.text.StringPrepParseException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.intanyoshanaoewen.footballmatch.API.APIrepository
import com.example.intanyoshanaoewen.footballmatch.API.MatchDetailPresenter
import com.example.intanyoshanaoewen.footballmatch.API.TeamPresenter
import com.example.intanyoshanaoewen.footballmatch.Model.Match
import com.example.intanyoshanaoewen.footballmatch.Model.Team
import com.example.intanyoshanaoewen.footballmatch.ModelView.MatchView
import com.example.intanyoshanaoewen.footballmatch.ModelView.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.card_match.*

/**
 * Created by Intan Yoshana Oewen on 13/11/2018.
 */

class MatchDetail : AppCompatActivity(), MatchView, TeamView {

    private lateinit var idTeamHome: String
    private lateinit var idTeamAway: String
    private lateinit var idMatch: String
    private lateinit var match: Match
    private lateinit var teamHome: Team
    private lateinit var teamAway: Team
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var matchDetailPresenter: MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        idMatch = intent.getStringExtra("matchId")
        idTeamHome = intent.getStringExtra("teamHome")
        idTeamAway = intent.getStringExtra("teamAway")

        val request = APIrepository()
        val gson = Gson()

        teamPresenter = TeamPresenter(this, request, gson)
        matchDetailPresenter = MatchDetailPresenter(this, request, gson)

        teamPresenter.getDetailTeamList(idTeamHome, idTeamAway)
        matchDetailPresenter.getDetailMatch(idMatch)
    }

    override fun isLoading(){
        progress_detail.visibility = View.VISIBLE
    }

    override fun stopLoading() {

        progress_detail.visibility = View.GONE

    }

    override fun showMatch(data: List<Match>?) {

        match = Match(
                data?.get(0)?.idEvent,
                data?.get(0)?.strHomeTeam,
                data?.get(0)?.strAwayTeam,
                data?.get(0)?.intHomeScore,
                data?.get(0)?.intAwayScore,
                data?.get(0)?.dateEvent,
                data?.get(0)?.strHomeLineupGoalkeeper,
                data?.get(0)?.strAwayLineupGoalkeeper,
                data?.get(0)?.strHomeGoalDetails,
                data?.get(0)?.strAwayGoalDetails,
                data?.get(0)?.intHomeShots,
                data?.get(0)?.intAwayShots,
                data?.get(0)?.strHomeLineupDefense,
                data?.get(0)?.awayDefense,
                data?.get(0)?.strAwayLineupDefense,
                data?.get(0)?.strAwayLineupMidfield,
                data?.get(0)?.strHomeLineupForward,
                data?.get(0)?.strAwayLineupForward,
                data?.get(0)?.strHomeLineupSubstitutes,
                data?.get(0)?.strAwayLineupSubstitutes,
                data?.get(0)?.strHomeFormation,
                data?.get(0)?.strAwayFormation,
                data?.get(0)?.strTeamBadge,
                data?.get(0)?.idHomeTeam,
                data?.get(0)?.idAwayTeam
        )


        date_of_match.text = match.dateEvent
        nameHome.text = match.strHomeTeam
        nameAway.text = match.strAwayTeam

        scoreHome.text = match.intHomeScore
        scoreAway.text = match.intAwayScore

        goalsHome.text = match.strHomeGoalDetails
        goalsAway.text = match.strAwayGoalDetails

        goalkeeperHome.text = match.strHomeLineupGoalkeeper
        goalkeeperAway.text = match.strAwayLineupGoalkeeper

        shotsHome.text = match.intHomeShots
        shotsAway.text = match.intAwayShots

        defenseHome.text = match.strHomeLineupDefense
        defenseAway.text = match.awayDefense

        forwardHome.text = match.strHomeLineupForward
        forwardAway.text = match.strAwayLineupForward

        substitutesHome.text = match.strHomeLineupSubstitutes
        substitutesAway.text = match.strAwayLineupSubstitutes

        midfieldHome.text = match.strAwayLineupDefense
        midfieldAway.text = match.strAwayLineupMidfield
    }

    override fun showTeam(dataHome: List<Team>?, dataAway: List<Team>) {
        teamHome = Team(dataHome?.get(0)?.strTeamBadge)
        teamAway = Team(dataAway?.get(0)?.strTeamBadge)
        Glide.with(this).load(dataHome?.get(0)?.strTeamBadge).into(imgHome)
        Glide.with(this).load(dataAway?.get(0)?.strTeamBadge).into(imgAway)
    }
}
