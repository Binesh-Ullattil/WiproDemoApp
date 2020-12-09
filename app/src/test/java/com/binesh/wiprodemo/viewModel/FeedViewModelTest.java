package com.binesh.wiprodemo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.binesh.wiprodemo.apis.ApiService;
import com.binesh.wiprodemo.enums.ManageStateList;
import com.binesh.wiprodemo.helper.ToastState;
import com.binesh.wiprodemo.model.Row;
import com.binesh.wiprodemo.repository.CountryFeedRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.mockito.Mockito.*;

public class FeedViewModelTest {
    //Field repository of type CountryFeedRepository - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    MutableLiveData<ToastState> toastState;
    @Mock
    Map<String, Object> mBagOfTags;
    @InjectMocks
    FeedViewModel feedViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFeeds() throws Exception {
        LiveData<ManageStateList<Row>> result = feedViewModel.getFeeds();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetRepository() throws Exception {
        CountryFeedRepository result = feedViewModel.getRepository();
        Assert.assertEquals(new CountryFeedRepository(new ApiService(null)), result);
    }

    @Test
    public void testSetRepository() throws Exception {
        feedViewModel.setRepository(new CountryFeedRepository(new ApiService(null)));
    }

    @Test
    public void testGetToastState() throws Exception {
        MutableLiveData<ToastState> result = feedViewModel.getToastState();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testSetToastState() throws Exception {
        feedViewModel.setToastState(null);
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

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme