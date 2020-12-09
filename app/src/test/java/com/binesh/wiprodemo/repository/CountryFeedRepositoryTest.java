package com.binesh.wiprodemo.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.lifecycle.MutableLiveData;
import com.binesh.wiprodemo.App;
import com.binesh.wiprodemo.apis.ApiService;
import com.binesh.wiprodemo.enums.ManageStateList;
import com.binesh.wiprodemo.helper.NetworkStatusHelper;
import com.binesh.wiprodemo.model.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.extension.listener.AnnotationEnabler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PowerMockListener;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")

@PrepareForTest({
        NetworkStatusHelper.class,
        CountryFeedRepository.class,
        App.class,
        NetworkInfo.class,
        ConnectivityManager.class
})


public class CountryFeedRepositoryTest {

    private CountryFeedRepository countryFeedRepository;

    @Mock
    Context mContext;

    @Mock
    NetworkStatusHelper networkStatusHelper;

    @Mock
    App app;

    @Mock
    NetworkInfo mNetworkInfo;

    @Mock
    ConnectivityManager connectivityManager;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(NetworkStatusHelper.class);
        networkStatusHelper.init(app);
        when(app.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        countryFeedRepository = new CountryFeedRepository(new ApiService(mContext));
    }


    @Test
    public void testLoadFeedsIfCase(){
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(mNetworkInfo);
        when(networkStatusHelper.isNetworkAvailable()).thenReturn(true);
        MutableLiveData<ManageStateList<Row>> result = countryFeedRepository.loadFeeds();
        Assert.assertNotNull(result);
    }

    @Test
    public void testLoadFeedsElseCase(){
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(mNetworkInfo);
        when(networkStatusHelper.isNetworkAvailable()).thenReturn(false);
        MutableLiveData<ManageStateList<Row>> result = countryFeedRepository.loadFeeds();

    }

    @Test
    public void testGetApiService() throws Exception {
        ApiService result = countryFeedRepository.getApiService();
        Assert.assertNotNull(result);
    }

    @Test
    public void testSetApiService() throws Exception {
        countryFeedRepository.setApiService(new ApiService(mContext));
    }
}

