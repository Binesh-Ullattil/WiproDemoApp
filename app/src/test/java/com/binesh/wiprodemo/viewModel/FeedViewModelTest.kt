package com.binesh.wiprodemo.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import com.binesh.wiprodemo.App
import com.binesh.wiprodemo.apis.ApiService
import com.binesh.wiprodemo.helper.AppConstants
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import com.binesh.wiprodemo.helper.ToastState
import com.binesh.wiprodemo.repository.CountryFeedRepository
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
class FeedViewModelTest {
    @Mock
    var countryFeedRepository: CountryFeedRepository? = null
    @Mock
    var mContext: Context? = null
    @Mock
    var networkStatusHelper: NetworkStatusHelper? = null
    @Mock
    var app: App? = null
    @Mock
    var mNetworkInfo: NetworkInfo? = null
    @Mock
    var connectivityManager: ConnectivityManager? = null
    @Mock
    var toastState: MutableLiveData<ToastState>? = null
    @Mock
    var mBagOfTags: Map<String, Any>? = null
    @InjectMocks
    var feedViewModel: FeedViewModel? = null

    lateinit var retrofit: Retrofit

    @Before
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
    }

    @Test
    @Throws(Exception::class)
    fun testGetFeeds() {
        val result =
            feedViewModel!!.getFeeds()
        Assert.assertEquals(null, result)
    }

    @Test
    @Throws(Exception::class)
    fun testGetRepository() {
        val result = feedViewModel!!.repository
        Assert.assertNotNull(result)
    }

    @Test
    @Throws(Exception::class)
    fun testSetRepository() {
        feedViewModel!!.repository = CountryFeedRepository(ApiService(retrofit))
    }

    @Test
    @Throws(Exception::class)
    fun testGetToastState() {
        val result = feedViewModel!!.toastState
        Assert.assertNotNull(result)
    }

    @Test
    @Throws(Exception::class)
    fun testSetToastState() {
        feedViewModel!!.toastState = toastState!!
    }

    @Test
    @Throws(Exception::class)
    fun testSetInfoToast() {
        feedViewModel!!.setInfoToast(0)
    }

    @Test
    @Throws(Exception::class)
    fun testSetSuccessToast() {
        feedViewModel!!.setSuccessToast(0)
    }

    @Test
    @Throws(Exception::class)
    fun testSetFailureToast() {
        feedViewModel!!.setFailureToast(0)
    }
}