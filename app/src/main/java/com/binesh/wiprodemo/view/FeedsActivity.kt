package com.binesh.wiprodemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.binesh.wiprodemo.R
import com.binesh.wiprodemo.base.BaseActivity

class FeedsActivity : BaseActivity() {

    override fun onBaseCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_feeds)

        findNavController(R.id.fragment).navigate(R.id.feedFragment)

    }
}
