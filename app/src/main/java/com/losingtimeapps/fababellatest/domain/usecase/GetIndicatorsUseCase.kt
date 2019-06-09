package com.losingtimeapps.fababellatest.domain.usecase

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Observable
import io.reactivex.Single

class GetIndicatorsUseCase(
    val indicatorRepository: IndicatorRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(): Observable<ResponseModel<List<Indicator>>> {

        return indicatorRepository.getIndicator()
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())

    }
}