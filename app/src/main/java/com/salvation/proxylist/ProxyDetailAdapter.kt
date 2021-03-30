package com.salvation.proxylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salvation.proxylist.databinding.ItemProxyDetailBinding

class ProxyDetailAdapter(var list: ArrayList<ProxyDetailModel>, var callback: ProxyDetailCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeCategoryViewHolder(
            ItemProxyDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private class HomeCategoryViewHolder(inflate: ItemProxyDetailBinding) :
        RecyclerView.ViewHolder(inflate.root) {
        val b: ItemProxyDetailBinding = inflate
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HomeCategoryViewHolder) {
            list[position].let { model ->
                holder.b.tvLabel.text = model.title
                holder.b.tvValue.text = model.value
                holder.itemView.setOnClickListener {
                    callback.onProxyDetailClicked(model.value)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}