package com.losingtimeapps.fababellatest.presentation.ui.indicatordescription

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.presentation.model.IndicatorModel
import com.losingtimeapps.fababellatest.presentation.ui.indicators.IndicatorsFragmentArgs
import kotlinx.android.synthetic.main.fragment_indicator_description.*


class IndicatorDescriptionFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_indicator_description, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val indicatorDescriptionArgs = IndicatorDescriptionFragmentArgs.fromBundle(arguments!!)
        initView(indicatorDescriptionArgs.indicatorData)

    }

    fun initView(indicatorModel: IndicatorModel) {
        tvName.text = indicatorModel.name
        tvCode.text = getString(R.string.code) + indicatorModel.code
        tvDate.text = indicatorModel.date
        tvValue.text = getString(R.string.value) + indicatorModel.value.toString()
        tvUnidadMedida.text = indicatorModel.measuredUnit
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
