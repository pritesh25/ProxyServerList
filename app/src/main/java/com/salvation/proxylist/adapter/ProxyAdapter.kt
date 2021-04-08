package com.salvation.proxylist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salvation.proxylist.R
import com.salvation.proxylist.databinding.ItemLayoutBinding
import com.salvation.proxylist.interfaces.ProxyCallback
import com.salvation.proxylist.model.ProxyDetailModel
import com.salvation.proxylist.room.ProxyEntity

class ProxyAdapter(
    var list: ArrayList<ProxyEntity>,
    var proxyCallback: ProxyCallback,
    var cxt: Context
) :
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

                holder.itemView.setOnClickListener {
                    proxyCallback.onProxySelected(getProxyList(model))
                }

                /*holder.b.tvIpAddress.setOnClickListener {
                    proxyCallback.onIpAddressClicked(model.ip)
                }

                holder.b.tvPort.setOnClickListener {
                    proxyCallback.onPortClicked(model.port.toString())
                }

                holder.b.tvProtocol.setOnClickListener {
                    proxyCallback.onProtocolClicked(model.protocol)
                }*/
            }
        }
    }

    private fun getProxyList(model: ProxyEntity): ArrayList<ProxyDetailModel> {
        return arrayListOf(
            ProxyDetailModel(cxt.getString(R.string.self), model.self),
            //ProxyDetailModel(cxt.getString(R.string.parent), model.parent),
            //ProxyDetailModel(cxt.getString(R.string.hit), model.hit),
            ProxyDetailModel(cxt.getString(R.string.count), model.count),
            ProxyDetailModel(cxt.getString(R.string.ip_address), model.ip),
            ProxyDetailModel(cxt.getString(R.string.port), model.port.toString()),
            ProxyDetailModel(cxt.getString(R.string.protocol), model.protocol),
            ProxyDetailModel(cxt.getString(R.string.anonymity), model.anonymity),
            ProxyDetailModel(cxt.getString(R.string.last_tested), model.lastTested),
            ProxyDetailModel(
                cxt.getString(R.string.allows_referer_header),
                model.allowsRefererHeader.toString()
            ),
            ProxyDetailModel(
                cxt.getString(R.string.allows_user_agent_header),
                model.allowsUserAgentHeader.toString()
            ),
            ProxyDetailModel(cxt.getString(R.string.allows_cookie), model.allowsCookies.toString()),
            ProxyDetailModel(cxt.getString(R.string.allows_post), model.allowsPost.toString()),
            ProxyDetailModel(cxt.getString(R.string.allows_https), model.allowsHttps.toString()),
            ProxyDetailModel(cxt.getString(R.string.country), model.country),
            ProxyDetailModel(cxt.getString(R.string.connect_time), model.connectTime),
            ProxyDetailModel(cxt.getString(R.string.download_speed), model.downloadSpeed),
            ProxyDetailModel(
                cxt.getString(R.string.second_to_first_byte),
                model.secondsToFirstByte
            ),
            ProxyDetailModel(cxt.getString(R.string.uptime), model.uptime),


            ProxyDetailModel(cxt.getString(R.string.city), model.city),
            ProxyDetailModel(cxt.getString(R.string.county), model.countryName),
            ProxyDetailModel(cxt.getString(R.string.continent), model.continentName),
            ProxyDetailModel(cxt.getString(R.string.longitude), model.longitude.toString()),
            ProxyDetailModel(cxt.getString(R.string.latitude), model.latitude.toString()),
            ProxyDetailModel(cxt.getString(R.string.type), model.type),
            ProxyDetailModel(cxt.getString(R.string.zip), model.zip)
        )
    }

    fun updateList(it: List<ProxyEntity?>) {
        list = it as ArrayList<ProxyEntity>
        notifyDataSetChanged()
    }
}