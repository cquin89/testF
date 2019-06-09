package com.losingtimeapps.fababellatest.presentation.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.losingtimeapps.fababellatest.R
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.usecase.*
import com.losingtimeapps.fababellatest.domain.utils.Error
import com.losingtimeapps.fababellatest.presentation.mapper.IndicatorViewMapper
import com.losingtimeapps.fababellatest.presentation.model.IndicatorModel
import com.losingtimeapps.fababellatest.presentation.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(
    val loginUseCase: LoginUseCase,
    val indicatorUseCase: GetIndicatorsUseCase,
    val getLocalAuthData: GetLocalAuthDataUseCase,
    val deleteLocalAuth: DeleteLocalAuthDataUseCase,
    val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    var firstTime: Boolean = true
    private val compositeDisposable = CompositeDisposable()

    var onLoginObservable = SingleLiveEvent<User>()

    var onRegisterObservable = SingleLiveEvent<User>()

    val onErrorObservable = SingleLiveEvent<Int>()

    val onGetUserObservable = MutableLiveData<User>()

    val onGetIndicatorObservable = MutableLiveData<List<IndicatorModel>>()

    var onDeleteAuthDataObserver = SingleLiveEvent<Boolean>()

    var user: User? = null
    var indicatorList: List<Indicator>? = null


    fun login(user: User) {
        compositeDisposable.add(
            loginUseCase.invoke(user)
                .subscribe(
                    {
                        if (it.dataIsEmpty())
                            showError(it.error)
                        else {
                            this.user = it.data!!
                            onLoginObservable.value = this.user
                        }
                    }, {
                        onErrorObservable.value = Error.UnexpectedError.value
                    }
                )
        )
    }

    fun register(user: User) {
        compositeDisposable.add(
            registerUserUseCase.invoke(user)
                .subscribe(
                    {
                    }, {
                        onErrorObservable.value = Error.UnexpectedError.value
                    }, {
                        onErrorObservable.value = R.string.register_success
                    }
                )
        )
    }

    fun showError(error: Error?) {
        onErrorObservable.value = error?.value
    }

    fun getIndicator() {
        compositeDisposable.add(
            indicatorUseCase.invoke().subscribe({
                if (it.dataIsEmpty())
                    showError(it.error)
                else {
                    indicatorList = it.data!!
                    onGetIndicatorObservable.value = IndicatorViewMapper().apply(indicatorList!!)
                    onLoginObservable.value = null
                }
            }, {
                onErrorObservable.value = Error.UnexpectedError.value
            })
        )

    }

    fun filterIndicatorList(newText: String) {

        if (indicatorList == null)
            return

        if (newText.isEmpty())
            onGetIndicatorObservable.value = IndicatorViewMapper().apply(indicatorList!!)

        val subList = indicatorList?.filter { it.code.toLowerCase().contains(newText.toLowerCase()) }
        if (subList != null) {
            onGetIndicatorObservable.value = IndicatorViewMapper().apply(subList)
        }

    }

    fun deleteUser() {
        compositeDisposable.add(
            deleteLocalAuth.invoke().subscribe({
                onDeleteAuthDataObserver.value = true
                logOut()
            }, {
                showError(Error.UnexpectedError)
            }, {
                onDeleteAuthDataObserver.value = true
                logOut()
            })
        )
    }

    fun getAuthData() {
        compositeDisposable.add(
            getLocalAuthData.invoke().subscribe({
                if (it.dataIsEmpty()) {
                    showError(it.error)
                } else {
                    this.user = it.data!!
                    onGetUserObservable.value = this.user
                }
            }, {
                onErrorObservable.value = Error.UnexpectedError.value

            })
        )

    }

    fun logOut() {
        user = null
        indicatorList = null
        firstTime = true
        compositeDisposable.clear()
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}
