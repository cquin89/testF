package com.losingtimeapps.fababellatest.data.mapper

import com.losingtimeapps.fababellatest.data.model.IndicatorResponse
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import java.util.*

class IndicatorMapper {

    fun apply(indicatorResponse: IndicatorResponse): ResponseModel<List<Indicator>> {
        return ResponseModel(
            Arrays.asList(
                indicatorResponse.getmUf().parse(),
                indicatorResponse.getmIvp().parse(),
                indicatorResponse.getmDolar().parse(),
                indicatorResponse.getmDolarIntercambio().parse(),
                indicatorResponse.getmEuro().parse(),
                indicatorResponse.getmIpc().parse(),
                indicatorResponse.getmUtm().parse(),
                indicatorResponse.getmImacec().parse(),
                indicatorResponse.getmTpm().parse(),
                indicatorResponse.getmLibraCobre().parse(),
                indicatorResponse.getmTasaDesempleo().parse(),
                indicatorResponse.getmBitcoin().parse()
            )
        )
    }
}