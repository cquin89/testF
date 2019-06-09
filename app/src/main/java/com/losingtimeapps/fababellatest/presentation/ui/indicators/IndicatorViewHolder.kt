package com.losingtimeapps.fababellatest.presentation.ui.indicators

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.presentation.model.IndicatorModel

class IndicatorHolder : RecyclerView.ViewHolder, IndicatorsAdapter.Binder<IndicatorModel> {
    override fun bind(data: IndicatorModel) {
        tvName.text = data.name
        tvValue.text = data.value.toString()
        clContainer.setOnClickListener { listener.onClick(data,it) }
    }

    var tvName: TextView
    var tvValue: TextView
    var clContainer: ConstraintLayout
    var listener: OnclickListener

    constructor(itemView: View, listener: OnclickListener) : super(itemView) {
        tvName = itemView.findViewById(R.id.tvName)
        tvValue = itemView.findViewById(R.id.tvValue)
        clContainer = itemView.findViewById(R.id.clContainer)
        this.listener = listener

    }

    interface OnclickListener {
        fun onClick(data: IndicatorModel,view:View)
    }

}