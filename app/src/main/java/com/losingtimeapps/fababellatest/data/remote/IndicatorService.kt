package com.losingtimeapps.fababellatest.data.remote

import com.losingtimeapps.fababellatest.data.model.IndicatorResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface IndicatorService {

    @GET(".")
    fun getGitHubRepository(
    ): Observable<IndicatorResponse>



}