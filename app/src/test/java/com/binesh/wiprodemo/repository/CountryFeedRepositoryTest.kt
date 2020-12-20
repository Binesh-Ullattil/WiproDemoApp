package com.binesh.wiprodemo.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.binesh.wiprodemo.App
import com.binesh.wiprodemo.apis.ApiService
import com.binesh.wiprodemo.helper.AppConstants
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(PowerMockRunner::class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(
    NetworkStatusHelper::class,
    CountryFeedRepository::class,
    App::class,
    NetworkInfo::class,
    ConnectivityManager::class
)
class CountryFeedRepositoryTest {
    private var countryFeedRepository: CountryFeedRepository? = null
    @Mock
    var networkStatusHelper: NetworkStatusHelper? = null
    @Mock
    var app: App? = null
    @Mock
    var mNetworkInfo: NetworkInfo? = null
    @Mock
    var connectivityManager: ConnectivityManager? = null
    lateinit var retrofit:Retrofit

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        PowerMockito.mockStatic(NetworkStatusHelper::class.java)
        networkStatusHelper!!.init(app!!)
        PowerMockito.`when`(app!!.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connectivityManager)

        retrofit=Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        countryFeedRepository = CountryFeedRepository(ApiService(retrofit))
    }

    @Test
    fun testLoadFeedsIfCase() {
        PowerMockito.`when`(connectivityManager!!.activeNetworkInfo)
            .thenReturn(mNetworkInfo)
        PowerMockito.`when`(networkStatusHelper!!.isNetworkAvailable())
            .thenReturn(true)
        val result =
            countryFeedRepository!!.loadFeeds()
        Assert.assertNotNull(result)
    }

    @Test
    fun testLoadFeedsElseCase() {
        PowerMockito.`when`(connectivityManager!!.activeNetworkInfo)
            .thenReturn(mNetworkInfo)
        PowerMockito.`when`(networkStatusHelper!!.isNetworkAvailable())
            .thenReturn(false)
        val result = countryFeedRepository!!.loadFeeds()
        Assert.assertNotNull(result)
    }

    @Test
    @Throws(Exception::class)
    fun testGetApiService() {
        val result = countryFeedRepository!!.apiService
        Assert.assertNotNull(result)
    }

    @Test
    @Throws(Exception::class)
    fun testSetApiService() {
        countryFeedRepository!!.apiService = ApiService(retrofit)
    }
}