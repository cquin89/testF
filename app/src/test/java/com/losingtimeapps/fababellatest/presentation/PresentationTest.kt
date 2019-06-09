package com.losingtimeapps.fababellatest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jraska.livedata.TestObserver
import com.losingtimeapps.fababellatest.*
import com.losingtimeapps.fababellatest.data.UserDataStore
import com.losingtimeapps.fababellatest.data.model.IndicatorResponse
import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import com.losingtimeapps.fababellatest.data.remote.IndicatorService
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.w3c.dom.Entity
import java.util.*

class PresentationTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    lateinit var indicatorService: IndicatorService
    @Mock
    lateinit var userDataStore: UserDataStore


    private val testScheduleImp: TestScheduleImp =
        TestScheduleImp()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        InjectorHelper.userDataStore = userDataStore
        InjectorHelper.indicatorService = indicatorService

        viewModel = InjectorHelper.injectMainViewModel()
    }

    //////// getAuthData
    @Test
    fun when_getAuthDataLogin_expectUser() {

        Mockito.`when`(
            userDataStore.getLocalAuthData()
        ).thenReturn(Observable.just(Arrays.asList(UserEntity("test"))))
        viewModel.getAuthData()
        val testSubscriber = viewModel.onGetUserObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertHasValue()
    }


    @Test
    fun when_getAuthDataNotLogin_expectError() {

        Mockito.`when`(
            userDataStore.getLocalAuthData()
        ).thenReturn(Observable.just(Arrays.asList()))
        viewModel.getAuthData()
        val testSubscriber = viewModel.onGetUserObservable.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testErrorSubscriber.awaitValue()
        testErrorSubscriber.assertHasValue()
        testSubscriber.assertNoValue()
    }

    ////////// Login

    @Test
    fun when_loginSucces_expectUser() {
        val user = User("dinamo", "12345")
        Mockito.`when`(
            userDataStore.loginRemoteServer(user)
        ).thenReturn(Observable.just(Arrays.asList(UserRemoteEntity("dinamo", "12345"))))

        Mockito.`when`(
            userDataStore.saveLocalAuthData(UserEntity("dinamo"))
        ).thenReturn(Completable.complete())


        viewModel.login(user)
        val testSubscriber = viewModel.onLoginObservable.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertHasValue()
        testErrorSubscriber.assertNoValue()
    }

    @Test
    fun when_loginFailed_expectError() {
        val user = User("dinamo", "12345")
        Mockito.`when`(
            userDataStore.loginRemoteServer(user)
        ).thenReturn(Observable.just(Arrays.asList(UserRemoteEntity("dinamo", "error"))))

        viewModel.login(user)
        val testSubscriber = viewModel.onLoginObservable.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testErrorSubscriber.awaitValue()
        testErrorSubscriber.assertHasValue()
        testSubscriber.assertNoValue()
    }

    //////////// getIndicator ///////////////

    @Test
    fun when_deleteLocalSuccess_expectTrue() {

        Mockito.`when`(
            userDataStore.deleteLocalAuthData()
        ).thenReturn(Completable.complete())

        viewModel.deleteUser()
        val testSubscriber = viewModel.onDeleteAuthDataObserver.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertValue(true)
    }

    @Test
    fun when_deleteLocalFailed_expectError() {

        Mockito.`when`(
            userDataStore.deleteLocalAuthData()
        ).thenReturn(Completable.error(Throwable("error")))

        viewModel.deleteUser()
        val testSubscriber = viewModel.onDeleteAuthDataObserver.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testErrorSubscriber.awaitValue()
        testErrorSubscriber.assertHasValue()
        testSubscriber.assertNoValue()
    }


    /////////////////////  Registration

    @Test
    fun when_registerSucces_expect() {
        val user = User("dinamo", "12345")
        Mockito.`when`(
            userDataStore.saveRemoteUser(UserRemoteEntity("dinamo", "12345"))
        ).thenReturn(Completable.complete())



        viewModel.register(user)
        val testSubscriber = viewModel.onErrorObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertValue(R.string.register_success)
    }

    @Test
    fun when_registerFailed_Expect_Error() {
        val user = User("dinamo", "12345")
        Mockito.`when`(
            userDataStore.saveRemoteUser(UserRemoteEntity("dinamo", "12345"))
        ).thenReturn(Completable.error(Throwable("error")))

        viewModel.register(user)
        val testSubscriber = viewModel.onErrorObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertValue(R.string.unexpected_error)
    }

    /////////////////// indicator

    @Test
    fun when_getIndicatorsFailed_Expect_Error() {
        val user = User("dinamo", "12345")
        Mockito.`when`(
            indicatorService.getGitHubRepository()
        ).thenReturn(Observable.just(IndicatorResponse()))

        viewModel.getIndicator()
        val testSubscriber = viewModel.onGetIndicatorObservable.test()
        val testErrorSubscriber = viewModel.onErrorObservable.test()

        testErrorSubscriber.awaitValue()
        testErrorSubscriber.assertHasValue()
        testSubscriber.assertNoValue()
    }

    private fun <T> LiveData<T>.test(): TestObserver<T> {
        return TestObserver.test(this)
    }
}

