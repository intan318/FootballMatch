package com.example.intanyoshanaoewen.footballmatch.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.intanyoshanaoewen.footballmatch.API.APIrepository
import com.example.intanyoshanaoewen.footballmatch.API.MatchPresenter
import com.example.intanyoshanaoewen.footballmatch.MatchDetail
import com.example.intanyoshanaoewen.footballmatch.Model.Match
import com.example.intanyoshanaoewen.footballmatch.ModelView.MatchView
import com.example.intanyoshanaoewen.footballmatch.R
import com.example.intanyoshanaoewen.footballmatch.RecyclerAdapter.MainAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Intan Yoshana Oewen on 13/11/2018.
 */

class LastMatchFragment : Fragment(), MatchView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var matchPresenter : MatchPresenter
    private lateinit var adapter: MainAdapter
    private var matches: MutableList<Match> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_layout.setOnRefreshListener(this)
        adapter = MainAdapter(this.context!!, matches) {
            startActivity(
                    intentFor<MatchDetail>(
                            "matchId" to it.idEvent, "teamHome" to it.idHomeTeam, "teamAway" to it.idAwayTeam
                    )
            )
        }

        match_recycler.layoutManager = LinearLayoutManager(activity)
        match_recycler.adapter = adapter

        val request = APIrepository()
        val gson = Gson()

        matchPresenter = MatchPresenter(this, request, gson)
        matchPresenter.getLastMatch()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onRefresh() {
        matchPresenter.getLastMatch()
    }

    override fun isLoading() {
        progress_circular.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        progress_circular.visibility = View.GONE
    }

    override fun showMatch(data: List<Match>?) {
        swipe_layout.isRefreshing = false
        matches.clear()
        data?.let {
            matches.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast(getString(R.string.kosong))
    }
}