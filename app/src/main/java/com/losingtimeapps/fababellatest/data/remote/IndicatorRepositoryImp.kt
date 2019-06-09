package com.losingtimeapps.fababellatest.data.remote

import com.losingtimeapps.fababellatest.data.mapper.IndicatorMapper
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.utils.ParseError
import io.reactivex.Observable

open class IndicatorRepositoryImp(
    private val indicatorService: IndicatorService,
    private val indicatorMapper: IndicatorMapper
) : IndicatorRepository {


    override fun getIndicator(): Observable<ResponseModel<List<Indicator>>> {
        return indicatorService.getGitHubRepository().map {
            indicatorMapper.apply(it)
        }.onErrorReturn { ResponseModel(ParseError().parse(it)) }
    }
}