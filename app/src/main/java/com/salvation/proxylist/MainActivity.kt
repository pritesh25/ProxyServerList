package com.salvation.proxylist

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.salvation.proxylist.databinding.ActivityMainBinding
import com.salvation.proxylist.databinding.BottomsheetLayoutBinding
import com.salvation.proxylist.room.ProxyEntity
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ProxyCallback, ProxyDetailCallback {

    /**
    {
    "_links": {
    "_self": "\/proxy",
    "_parent": "\/"
    },
    "error": "you've exceeded your daily usage, wait 24 hours or purchase an api key here https:\/\/getproxylist.com#pricing"
    }
     */

    private val mTag = "MainActivity"
    private val proxyViewModel: ProxyViewModel by viewModels()

    private var clipboard: ClipboardManager? = null

    private var snackbar: Snackbar? = null
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        initView()
        getViewModel()
        viewListener()

    }

    private fun initView() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        snackbar = b.root.showInternetError(applicationContext, "")

        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        b.swipeRefreshLayout.isRefreshing = true
    }

    private fun getViewModel() {

        val proxyAdapter = ProxyAdapter(arrayListOf(), this, applicationContext)
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
                                        model.links?.self,
                                        model.links?.parent,
                                        model.cache?.key,
                                        model.cache?.hit,
                                        model.stats?.count,
                                        model.ip,
                                        model.port,
                                        model.protocol,
                                        model.anonymity,
                                        model.lastTested,
                                        model.allowsRefererHeader,
                                        model.allowsUserAgentHeader,
                                        model.allowsCookies,
                                        model.allowsPost,
                                        model.allowsHttps,
                                        model.country,
                                        model.connectTime,
                                        model.downloadSpeed,
                                        model.secondsToFirstByte,
                                        model.uptime
                                    )
                                )
                            } ?: kotlin.run {
                                snackbar?.setText(R.string.oops_something_went_wrong)
                                snackbar?.show()
                            }
                        }
                        else -> {

                            response.responseErrorBody?.let { responseErrorBody ->
                                val jsonObject = JSONObject(responseErrorBody.string())
                                log(mTag, "jsonObject = ${jsonObject.getString("error")}")

                                snackbar?.setText(jsonObject.getString("error"))
                                snackbar?.show()
                            }
                        }
                    }
                } ?: run {
                    snackbar?.setText(R.string.unable_to_connect_server)
                    snackbar?.show()
                }
            }
        }
        proxyViewModel.setProxyRequest()
        proxyViewModel.proxyList?.observe(this) {
            it?.let {
                proxyAdapter.updateList(it)
            }
        }

    }

    private fun viewListener() {
        b.swipeRefreshLayout.setOnRefreshListener {
            proxyViewModel.setProxyRequest()
        }
    }

    override fun onProxySelected(protocol: ArrayList<ProxyDetailModel>) {
        showBottomSheetDialog(protocol)
    }

    private var mBottomSheetDialog: BottomSheetDialog? = null
    private fun showBottomSheetDialog(protocol: ArrayList<ProxyDetailModel>) {
        mBottomSheetDialog = BottomSheetDialog(this)
        val binding: BottomsheetLayoutBinding =
            BottomsheetLayoutBinding.inflate(LayoutInflater.from(applicationContext))
        mBottomSheetDialog?.let {
            it.setContentView(binding.root)
            val proxyDetailAdapter = ProxyDetailAdapter(protocol, this)
            binding.recyclerViewDetail.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = proxyDetailAdapter
            }
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    override fun onProxyDetailClicked(s: String?) {
        clipboard?.setPrimaryClip(ClipData.newPlainText(null, s))
        toasty(applicationContext, getString(R.string.data_copied, s))
    }
}