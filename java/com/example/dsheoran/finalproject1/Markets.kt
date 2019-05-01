package com.example.dsheoran.finalproject1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dsheoran.finalproject1.R.id.*
import com.example.dsheoran.finalproject1.R.layout.activity_markets
import com.example.dsheoran.finalproject1.R.id.center

import kotlinx.android.synthetic.main.activity_markets.*


class Markets : AppCompatActivity(),Stocks.SetONCLick,Cryptocurrency.SetONCLickCrypto {
    override fun EventclickCrypto(data1: CryptoJSON) {
        var intent:Intent= Intent(this,PurchaseCrypto::class.java)
        intent.putExtra("crypto_data",data1)
        startActivity(intent)
    }

    override fun Eventclick(data: StocksJSON) {
        var intent:Intent= Intent(this,Purchase::class.java)
        intent.putExtra("stock_data",data)
        startActivity(intent)
    }

    lateinit var mypagerAdapter: MyFragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_markets)

//        setSupportActionBar (findViewById(R.id.toolbar))
//        val appBar = supportActionBar
//
//        appBar!!.setDisplayShowTitleEnabled(false)
//        appBar.setDisplayShowHomeEnabled ( true )
//        appBar.setLogo (R.drawable.abc_ic_star_black_36dp)
//        appBar.setDisplayUseLogoEnabled(true)

        var types=ArrayList<String>()
        types.add("Stocks")
        types.add("CryptoCurrency")

        mypagerAdapter = MyFragmentStatePagerAdapter(supportFragmentManager,types)
        view_pager.adapter=mypagerAdapter
        view_pager.currentItem=0
        view_pager.pageMargin=10
        tab_movies.setupWithViewPager(view_pager)
        tab_movies.tabGravity= center
    }
}
