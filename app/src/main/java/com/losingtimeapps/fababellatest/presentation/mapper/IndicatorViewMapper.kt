package com.losingtimeapps.fababellatest.presentation.mapper

import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.presentation.model.IndicatorModel

class IndicatorViewMapper {

    fun apply(indicatorList: List<Indicator>): List<IndicatorModel> {

        return indicatorList.map {
            parse(it)
        }
    }

    private fun parse(indicator: Indicator): IndicatorModel {
        return IndicatorModel(
            code = indicator.code,
            name = indicator.name,
            measuredUnit = indicator.measuredUnit,
            date = indicator.date,
            value = indicator.value
        )
    }
}