package com.losingtimeapps.fababellatest.presentation.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.losingtimeapps.fababellatest.presentation.ui.indicatordescription.IndicatorDescriptionFragment
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.presentation.ui.indicators.IndicatorsFragment
import com.losingtimeapps.fababellatest.presentation.ui.login.LoginFragment
import com.losingtimeapps.fababellatest.presentation.ui.splash.SplashFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_activity.*
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.losingtimeapps.fababellatest.app.App
import com.losingtimeapps.fababellatest.di.modules.ActivityModule
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    LoginFragment.OnFragmentInteractionListener,
    IndicatorsFragment.OnFragmentInteractionListener,
    IndicatorDescriptionFragment.OnFragmentInteractionListener,
    SplashFragment.OnFragmentInteractionListener, SearchView.OnQueryTextListener {


    override fun getMainViewModel(): MainViewModel = viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initComponent()

        viewModel.onGetUserObservable.observe(this, Observer {
            title = "Hola " + it.userName
        })


        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.loginFragment2 -> {
                    title = ""
                }

                R.id.indicatorsFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.indicatorDescriptionFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }

            }
        }
    }


    @Inject
    lateinit var viewModel: MainViewModel


    fun initComponent() {
        val app = application as App
        val activityModule = ActivityModule(this)
        app.getComponent().createMainActivityComponent(activityModule).inject(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.filterIndicatorList(newText ?: "")
        return false
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("awdd", "--->$uri")
    }

    override fun logOut() {
        viewModel.deleteLocalAuth
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dawd = item.itemId
        when (dawd) {
            android.R.id.home -> onBackPressed()
            R.id.log_out -> viewModel.deleteUser()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {

        val currentFragment = nav_host_fragment.childFragmentManager.fragments.get(0)
        val controller = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (currentFragment is OnBackPressedListener)
            (currentFragment as OnBackPressedListener).onBackPressed()
        else if (!controller.popBackStack())
            finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}


interface OnBackPressedListener {
    fun onBackPressed()
}
