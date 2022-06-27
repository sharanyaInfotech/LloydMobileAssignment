package com.assignment.test.coroutine

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.assignment.test.Api.ApiService
import com.assignment.test.models.VehicleDataModel
import com.assignment.test.models.VehicleItem
import com.assignment.test.repository.VehicleDetailsRepository
import com.assignment.test.viewmodel.VehicleDetailsViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class VehicleViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiService

    @Mock
    private lateinit var repoHelper: VehicleDetailsRepository

    @Mock
    lateinit var vehicleViewModel:VehicleDetailsViewModel

    @Mock
    private lateinit var apiVehicleObserver: Observer<List<VehicleItem>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        //apiHelper = ApiService
        vehicleViewModel = VehicleDetailsViewModel(apiHelper)
    }

    //success
    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<VehicleItem>())
            //doReturn(vehicleViewModel.responseVeh)
                .`when`(apiHelper)
                .getVehicles()
            val viewModel = VehicleDetailsViewModel(apiHelper)
            viewModel.responseVeh.observeForever(apiVehicleObserver)
            verify(apiHelper).getVehicles()
            verify(apiVehicleObserver).onChanged(emptyList<VehicleItem>())
            viewModel.responseVeh.removeObserver(apiVehicleObserver)
        }
    }

    //error
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getVehicles()
            val viewModel = VehicleDetailsViewModel(apiHelper)
            viewModel.responseVeh.observeForever(apiVehicleObserver)
            verify(apiHelper).getVehicles()
            verify(apiVehicleObserver).onChanged(null)
            viewModel.responseVeh.removeObserver(apiVehicleObserver)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }


}
