/**
 * Class used to show the data in Recycler view
 */
package com.binesh.wiprodemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.binesh.wiprodemo.BR
import com.binesh.wiprodemo.R
import com.binesh.wiprodemo.databinding.RowItemFeedBinding
import com.binesh.wiprodemo.model.Row

class FeedsAdapter(
    private val _list: MutableList<Row>
) :
    RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {
    private val list = _list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind: RowItemFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_feed,
            parent,
            false
        )
        return ViewHolder(bind)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(private val _bind: RowItemFeedBinding) : RecyclerView.ViewHolder(_bind.root) {
        public fun bindItems(
            item: Row
        ) {
            _bind.setVariable(BR.item, item)
            _bind.executePendingBindings()
        }
    }

    fun addItems(dataList: MutableList<Row>) {
        list.clear()
        list.addAll(dataList)
        notifyDataSetChanged()
    }


    fun clearAllItems() {
        list.clear()
        notifyDataSetChanged()
    }
}