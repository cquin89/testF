package com.losingtimeapps.fababellatest

import com.google.gson.annotations.SerializedName
import com.losingtimeapps.fababellatest.data.model.IndicatorEntity
import com.losingtimeapps.fababellatest.data.model.IndicatorResponse
import org.mockito.Mock

class IndicatorResponseMock : IndicatorResponse(){

   @Mock
    private var mAutor: String? = null
    @SerializedName("bitcoin")
    private var mBitcoin: IndicatorEntity? = null
    @SerializedName("dolar")
    private var mDolar: IndicatorEntity? = null
    @SerializedName("dolar_intercambio")
    private var mDolarIntercambio: IndicatorEntity? = null
    @SerializedName("euro")
    private var mEuro: IndicatorEntity? = null
    @SerializedName("fecha")
    private var mFecha: String? = null
    @SerializedName("imacec")
    private var mImacec: IndicatorEntity? = null
    @SerializedName("ipc")
    private var mIpc: IndicatorEntity? = null
    @SerializedName("ivp")
    private var mIvp: IndicatorEntity? = null
    @SerializedName("libra_cobre")
    private var mLibraCobre: IndicatorEntity? = null
    @SerializedName("tasa_desempleo")
    private var mTasaDesempleo: IndicatorEntity? = null
    @SerializedName("tpm")
    private var mTpm: IndicatorEntity? = null
    @SerializedName("uf")
    private var mUf: IndicatorEntity? = null
    @SerializedName("utm")
    private var mUtm: IndicatorEntity? = null
    @SerializedName("version")
    private var mVersion: String? = null
}