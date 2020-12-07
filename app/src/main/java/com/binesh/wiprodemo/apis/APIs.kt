package com.binesh.wiprodemo.apis

import com.binesh.wiprodemo.model.MovieFeedResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface APIs {
    @GET("facts.json")
    fun countryFeed(): Observable<MovieFeedResponseModel>
}