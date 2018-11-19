package com.example.intanyoshanaoewen.footballmatch.RecyclerAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intanyoshanaoewen.footballmatch.Model.Match
import com.example.intanyoshanaoewen.footballmatch.R

import kotlinx.android.synthetic.main.card_match.view.*

class MainAdapter(private val context : Context, private val matches : List<Match>, private val listener : (Match) -> Unit ):
        RecyclerView.Adapter<MainAdapter.TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder  {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match, parent, false))
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder (holder: TeamViewHolder, position: Int ) {
        holder.bindMatch(matches[position], listener)
    }

    class TeamViewHolder (private val containerView: View) : RecyclerView.ViewHolder (containerView){

        @SuppressLint("SetTextI18n")
        fun bindMatch(match : Match, listener : (Match) -> Unit){
            containerView.match_date.text = match.dateEvent

            if(match.intHomeScore == null){
                match.intHomeScore == ""
                match.intAwayScore == ""
            }

            containerView.teamHome.text = match.strHomeTeam
            containerView.teamAway.text = match.strAwayTeam
            containerView.score.text = match.intHomeScore + " VS " + match.intAwayScore
            containerView.setOnClickListener{listener(match)}
        }
    }
}

