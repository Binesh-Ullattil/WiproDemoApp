package com.binesh.wiprodemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.binesh.wiprodemo.BR

import com.binesh.wiprodemo.R
import com.binesh.wiprodemo.adapter.FeedsAdapter
import com.binesh.wiprodemo.base.AppInjectBindFragment
import com.binesh.wiprodemo.databinding.FragmentFeedsBinding
import com.binesh.wiprodemo.enums.ManageStatusEnum
import com.binesh.wiprodemo.helper.ToastHelper
import com.binesh.wiprodemo.viewModel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FeedsFragment : AppInjectBindFragment() {

    @Inject
    lateinit var viewModel: FeedViewModel

    lateinit var binding: FragmentFeedsBinding


    private fun initView() {

        binding.rvFeeds.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedsAdapter(mutableListOf())
        }

        binding.refreshLayout.setOnRefreshListener {
            loadFeeds()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun loadFeeds() {

        viewModel.getFeeds().observe(viewLifecycleOwner, Observer {

            when (it.status) {
                ManageStatusEnum.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

                ManageStatusEnum.LOADING_DISMISS -> {
                    progressBar.visibility = View.GONE
                }

                ManageStatusEnum.NO_DATA_FOUND -> {
                    ToastHelper.showToast(requireContext(), getString(R.string.no_data_found))
                }

                ManageStatusEnum.NO_INTERNET_CONNECTION -> {
                    ToastHelper.showToast(
                        requireContext(),
                        getString(R.string.no_internet_connection)
                    )
                }

                ManageStatusEnum.FAILED -> {
                    progressBar.visibility = View.GONE
                    ToastHelper.showToast(requireContext(), getString(R.string.loading_failed))
                }

                ManageStatusEnum.SUCCESS -> {
                    toolbar.tvToolbarTitle.text = it.title
                    (binding.rvFeeds.adapter as FeedsAdapter).addItems(
                        it.dataList ?: mutableListOf()
                    )
                }

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feeds, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        loadFeeds()
    }

}
