package com.example.intanyoshanaoewen.footballmatch.API

import java.net.URL

/**
 * Created by Intan Yoshana Oewen on 13/11/2018.
 */
class APIrepository {
    fun doRequest (url: String): String {
        return URL(url).readText()
    }
}