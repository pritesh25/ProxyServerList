package com.salvation.proxylist

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.salvation.proxylist.databinding.ActivityMainBinding
import com.salvation.proxylist.room.ProxyEntity
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ProxyCallback {

    /**
    {
    "_links": {
    "_self": "\/proxy",
    "_parent": "\/"
    },
    "error": "you've exceeded your daily usage, wait 24 hours or purchase an api key here https:\/\/getproxylist.com#pricing"
    }
     */

    private val proxyViewModel: ProxyViewModel by viewModels()

    private var clipboard: ClipboardManager? = null

    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


        val proxyAdapter = ProxyAdapter(arrayListOf(), this)
        b.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = proxyAdapter
        }

        proxyViewModel.init()
        proxyViewModel.getProxyRequest()?.observe(this) {

            b.swipeRefreshLayout.isRefreshing = false
            b.progressBar.visibility = View.GONE

            it?.let { response ->
                response.statusCode?.let { statusCode ->
                    when (statusCode) {
                        STATUS_200 -> {
                            response.model?.let { model ->

                                proxyViewModel.insertProxy(
                                    ProxyEntity(
                                        model.ip,
                                        model.port,
                                        model.protocol
                                    )
                                )
                            } ?: kotlin.run {
                                showDialog(this, R.string.oops_something_went_wrong) {
                                    finish()
                                }
                            }
                        }
                        else -> {

                            response.responseErrorBody?.let { responseErrorBody ->
                                val jsonObject = JSONObject(responseErrorBody.string())
                                log(
                                    mTag = "MainActivity",
                                    "jsonObject = ${jsonObject.getString("error")}"
                                )

                                showDialog(this, jsonObject.getString("error")) {
                                    finish()
                                }

                            }

                        }
                    }
                } ?: run {
                    showDialog(applicationContext, R.string.unable_to_connect_server) {
                        finish()
                    }
                }

            }
        }

        b.swipeRefreshLayout.setOnRefreshListener {
            proxyViewModel.setProxyRequest()
        }

        b.swipeRefreshLayout.isRefreshing = true
        proxyViewModel.setProxyRequest()

        proxyViewModel.proxyList?.observe(this) {
            it?.let {
                proxyAdapter.updateList(it)
            }
        }

    }

    override fun onIpAddressClicked(ipAddress: String?) {
        clipboard?.setPrimaryClip(ClipData.newPlainText(null, ipAddress))
        toasty(applicationContext, getString(R.string.ip_address_copied))
    }

    override fun onPortClicked(port: String?) {
        clipboard?.setPrimaryClip(ClipData.newPlainText(null, port))
        toasty(applicationContext, getString(R.string.port_copied))
    }

    override fun onProtocolClicked(protocol: String?) {
        clipboard?.setPrimaryClip(ClipData.newPlainText(null, protocol))
        toasty(applicationContext, getString(R.string.proxy_copied))
    }
}