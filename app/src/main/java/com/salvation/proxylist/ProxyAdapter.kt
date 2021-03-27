package com.salvation.proxylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salvation.proxylist.databinding.ItemLayoutBinding
import com.salvation.proxylist.room.ProxyEntity

class ProxyAdapter(var list: ArrayList<ProxyEntity>, var proxyCallback: ProxyCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mTag = "ProxyAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProxyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ProxyViewHolder(view: ItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val b: ItemLayoutBinding = view
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProxyViewHolder) {

            list[position].let { model ->

                holder.b.tvIpAddress.text = model.ip
                holder.b.tvPort.text = model.port.toString()
                holder.b.tvProtocol.text = model.protocol

                holder.b.tvIpAddress.setOnClickListener {
                    proxyCallback.onIpAddressClicked(model.ip)
                }

                holder.b.tvPort.setOnClickListener {
                    proxyCallback.onPortClicked(model.port.toString())
                }

                holder.b.tvProtocol.setOnClickListener {
                    proxyCallback.onProtocolClicked(model.protocol)
                }

            }
        }
    }

    fun updateList(it: List<ProxyEntity?>) {
        list = it as ArrayList<ProxyEntity>
        notifyDataSetChanged()
    }
}