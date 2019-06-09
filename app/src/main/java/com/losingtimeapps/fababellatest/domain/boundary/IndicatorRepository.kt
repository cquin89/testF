package com.losingtimeapps.fababellatest.domain.boundary

import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface IndicatorRepository {

    fun getIndicator(): Observable<ResponseModel<List<Indicator>>>

}