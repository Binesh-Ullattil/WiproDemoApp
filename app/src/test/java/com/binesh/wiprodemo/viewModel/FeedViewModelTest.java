package com.binesh.wiprodemo.viewModel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.binesh.wiprodemo.App;
import com.binesh.wiprodemo.apis.ApiService;
import com.binesh.wiprodemo.enums.ManageStateList;
import com.binesh.wiprodemo.helper.NetworkStatusHelper;
import com.binesh.wiprodemo.helper.ToastState;
import com.binesh.wiprodemo.model.Row;
import com.binesh.wiprodemo.repository.CountryFeedRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.mockito.Mockito.*;
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

public class FeedViewModelTest {

    @Mock
    CountryFeedRepository countryFeedRepository;
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
    @Mock
    MutableLiveData<ToastState> toastState;
    @Mock
    Map<String, Object> mBagOfTags;
    @InjectMocks
    FeedViewModel feedViewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(NetworkStatusHelper.class);
        networkStatusHelper.init(app);
        when(app.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
    }

    @Test
    public void testGetFeeds() throws Exception {
        LiveData<ManageStateList<Row>> result = feedViewModel.getFeeds();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetRepository() throws Exception {
        CountryFeedRepository result = feedViewModel.getRepository();
        Assert.assertNotNull(result);

    }

    @Test
    public void testSetRepository() throws Exception {
        feedViewModel.setRepository(new CountryFeedRepository(new ApiService(null)));
    }

    @Test
    public void testGetToastState() throws Exception {
        MutableLiveData<ToastState> result = feedViewModel.getToastState();
        Assert.assertNotNull(result);
    }

    @Test
    public void testSetToastState() throws Exception {
        feedViewModel.setToastState(toastState);
    }

    @Test
    public void testSetInfoToast() throws Exception {
        feedViewModel.setInfoToast(0);
    }

    @Test
    public void testSetSuccessToast() throws Exception {
        feedViewModel.setSuccessToast(0);
    }

    @Test
    public void testSetFailureToast() throws Exception {
        feedViewModel.setFailureToast(0);
    }
}
