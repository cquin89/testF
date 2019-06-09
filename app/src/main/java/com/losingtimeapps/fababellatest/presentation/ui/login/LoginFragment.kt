package com.losingtimeapps.fababellatest.presentation.ui.login

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_login.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = listener!!.getMainViewModel()
        val loginButton = view?.findViewById<Button>(R.id.loginButton)
        val etUserName: EditText = etUserName


        loginButton?.setOnClickListener {
            viewModel.login(User(etUserName.text.toString(), etPassword.text.toString()))
        }
        registerButton?.setOnClickListener {
            viewModel.register(User(etUserName.text.toString(), etPassword.text.toString()))
        }

        viewModel.onLoginObservable.observe(this, Observer {
            if (it != null)
                Navigation.findNavController(view!!)
                    .navigate(LoginFragmentDirections.loginToIndicators(etUserName.text.toString()))
        })


        viewModel.onErrorObservable.observe(this, Observer {
            Toast.makeText(activity, getString(it), Toast.LENGTH_SHORT).show()
        })

    }

    // TODO: Rename method, update argument and hook method into UI event
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

        fun getMainViewModel(): MainViewModel
    }

}
