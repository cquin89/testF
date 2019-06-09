package com.losingtimeapps.fababellatest.domain

import com.losingtimeapps.fababellatest.TestScheduleImp
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.usecase.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Observable
import java.util.*


class DomainUnitTest {

    private lateinit var getIndicators: GetIndicatorsUseCase
    private lateinit var getLocalAuthData: GetLocalAuthDataUseCase
    private lateinit var deleteLocalAuthData: DeleteLocalAuthDataUseCase
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var registerUser: RegisterUserUseCase


    @Mock
    lateinit var indicatorRepository: IndicatorRepository

    @Mock
    lateinit var userRepository: UserRepository

    private val testScheduleImp: TestScheduleImp =
        TestScheduleImp()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getIndicators = GetIndicatorsUseCase(indicatorRepository, testScheduleImp)
        getLocalAuthData = GetLocalAuthDataUseCase(userRepository, testScheduleImp)
        deleteLocalAuthData = DeleteLocalAuthDataUseCase(userRepository, testScheduleImp)
        loginUseCase = LoginUseCase(userRepository, testScheduleImp)
        registerUser = RegisterUserUseCase(userRepository, testScheduleImp)
    }

    //////////////// login ///////////////////////////////////////////////////
    @Test
    fun when_LoginEmptyName_Expect_EmptyUserNameError() {

        val testSubscriber = loginUseCase.invoke(User("", "")).test()
        testSubscriber.assertValue(ResponseModel(Error.EmptyUserNameError))
        testSubscriber.dispose()
    }

    @Test
    fun when_LoginEmptyPassword_Expect_EmptyPasswordNameError() {

        val testSubscriber = loginUseCase.invoke(User("Kratos", "")).test()
        testSubscriber.assertValue(ResponseModel(Error.EmptyPasswordError))
        testSubscriber.dispose()
    }

    @Test
    fun when_LoginInvalidData_Expect_InvalidDataError() {
        val user = User("Kratos", "password")
        Mockito.`when`(
            userRepository.login(user)
        ).thenReturn(Observable.just(ResponseModel(Error.InvalidDataError)))

        val testSubscriber = loginUseCase.invoke(user).test()
        testSubscriber.assertValue(ResponseModel(Error.InvalidDataError))
        testSubscriber.dispose()
    }

    @Test
    fun when_LoginValidData_Expect_User() {
        val user = User("Kratos", "password")
        Mockito.`when`(
            userRepository.login(user)
        ).thenReturn(Observable.just(ResponseModel(user)))

        Mockito.`when`(
            userRepository.saveLocalAuthData(user)
        ).thenReturn(Observable.just(ResponseModel(user)))

        val testSubscriber = loginUseCase.invoke(user).test()
        testSubscriber.assertValue(ResponseModel(user))
        testSubscriber.dispose()
        Mockito.verify(userRepository).saveLocalAuthData(user)
    }

    //////////////// REGISTER ///////////////////////////////////////////////////
    @Test
    fun when_RegisterEmptyUserName_ExpectEmptyUserNameError() {

        val testSubscriber = registerUser.invoke(User("", "")).test()
        testSubscriber.assertValue(ResponseModel(Error.EmptyUserNameError))
        testSubscriber.dispose()
    }

    @Test
    fun when_RegisterEmptyPassword_Expect_EmptyPasswordNameError() {

        val testSubscriber = registerUser.invoke(User("Kratos", "")).test()
        testSubscriber.assertValue(ResponseModel(Error.EmptyPasswordError))
        testSubscriber.dispose()
    }

    @Test
    fun when_RegisterDuplicateUserName_Expect_UserAlreadyRegisteredError() {
        val user = User("Kratos", "password")
        Mockito.`when`(
            userRepository.registerUser(user)
        ).thenReturn(Observable.just(ResponseModel(Error.UserAlreadyRegisteredError)))

        val testSubscriber = registerUser.invoke(user).test()
        testSubscriber.assertValue(ResponseModel(Error.UserAlreadyRegisteredError))
        testSubscriber.dispose()
    }


    //////////////////////////// getLocalAuthData /////////////////////////////////////////////////////
    @Test
    fun when_GetLocalData_NotLoginError() {
        Mockito.`when`(
            userRepository.getLocalAuthData()
        ).thenReturn(Observable.just(ResponseModel(Error.UserNotLoginError)))

        val testSubscriber = getLocalAuthData.invoke().test()
        testSubscriber.assertValue(ResponseModel(Error.UserNotLoginError))
        testSubscriber.dispose()
    }

    @Test
    fun when_GetLocalData_GetUser() {

        Mockito.`when`(
            userRepository.getLocalAuthData()
        ).thenReturn(Observable.just(ResponseModel(User("Kratos", "password"))))

        val testSubscriber = getLocalAuthData.invoke().test()
        testSubscriber.assertValue(ResponseModel(User("Kratos", "password")))
        testSubscriber.dispose()
    }

    //////////////////////////////////      delete local auth data   ///////////////////////////////////////////////

    @Test
    fun when_DeleteLocalData_NotLoginError() {
        Mockito.`when`(
            userRepository.deleteLocalAuthData()
        ).thenReturn(Observable.just(ResponseModel(Error.UserNotLoginError)))

        val testSubscriber = deleteLocalAuthData.invoke().test()
        testSubscriber.assertValue(ResponseModel(Error.UserNotLoginError))
        testSubscriber.dispose()
    }

    @Test
    fun when_DeleteLocalData_GetUser() {

        Mockito.`when`(
            userRepository.deleteLocalAuthData()
        ).thenReturn(Observable.just(ResponseModel(User("Kratos", "password"))))

        val testSubscriber = deleteLocalAuthData.invoke().test()
        testSubscriber.assertValue(ResponseModel(User("Kratos", "password")))
        testSubscriber.dispose()
    }

    ////////////////////////// get Indicators ////////////////////////////////////////////


    @Test
    fun when_GetIndicators_IndicatorList() {
        val responseModel = ResponseModel(Arrays.asList(Indicator("", "", "", "", 0.0)))
        Mockito.`when`(
            indicatorRepository.getIndicator()
        ).thenReturn(Observable.just(responseModel))

        val testSubscriber = getIndicators.invoke().test()
        testSubscriber.assertValue(responseModel)
        testSubscriber.dispose()
    }


    @Test
    fun when_GetIndicators_IndicatorError() {
        val responseModel = ResponseModel<List<Indicator>>(Error.GetIndicatorsError)
        Mockito.`when`(
            indicatorRepository.getIndicator()
        ).thenReturn(Observable.just(responseModel))

        val testSubscriber = getIndicators.invoke().test()
        testSubscriber.assertValue(responseModel)
        testSubscriber.dispose()
    }
}
