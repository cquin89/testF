package com.losingtimeapps.fababellatest.data.model


import com.google.gson.annotations.SerializedName
import com.losingtimeapps.fababellatest.domain.entity.Indicator


class IndicatorEntity(
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("fecha")
    val fecha: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("unidad_medida")
    val unidadMedida: String,
    @SerializedName("valor")
    var valor: Double
) {
    fun parse(): Indicator {
        return Indicator(
            code = codigo,
            name = nombre,
            measuredUnit = unidadMedida,
            date = fecha,
            value = valor
        )
    }
}
