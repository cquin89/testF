package com.losingtimeapps.fababellatest.presentation.ui.indicators

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.presentation.model.IndicatorModel
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_indicators.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IndicatorsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IndicatorsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IndicatorsFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_indicators, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.action_seach3)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(activity as SearchView.OnQueryTextListener)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = listener!!.getMainViewModel()

        srlContainer.setOnRefreshListener {
            viewModel.getIndicator()
        }

        rvIndicators.layoutManager = LinearLayoutManager(context)
        rvIndicators.setHasFixedSize(true)

        val adapter = initAdapter()
        rvIndicators.adapter = adapter

        viewModel.onGetIndicatorObservable.observe(this, Observer {
            srlContainer.isRefreshing = false
            adapter.listItems = it
            adapter.notifyDataSetChanged()
        })
        viewModel.onDeleteAuthDataObserver.observe(this, Observer {
            if (it == true)
                Navigation.findNavController(view!!)
                    .navigate(IndicatorsFragmentDirections.indicatorToLogin())
        })

        if (viewModel.firstTime) {
            viewModel.firstTime = false
            viewModel.getIndicator()
            viewModel.getAuthData()

        }
    }

    fun initAdapter(): IndicatorsAdapter<IndicatorModel> {
        return object : IndicatorsAdapter<IndicatorModel>(), IndicatorHolder.OnclickListener {
            override fun onClick(data: IndicatorModel, view: View) {
                Navigation.findNavController(view)
                    .navigate(IndicatorsFragmentDirections.indicatorsToIndicatorDescription(data))
            }


            override fun getLayoutId(position: Int, obj: IndicatorModel): Int {
                return R.layout.item_indicator
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return IndicatorHolder(view, this)
            }
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun logOut()

        fun getMainViewModel():MainViewModel

    }
}
