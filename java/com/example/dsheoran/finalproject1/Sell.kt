package com.example.dsheoran.finalproject1

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dsheoran.finalproject1.R.id.sell_qty
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sell.*
import org.json.JSONObject
import java.lang.ref.WeakReference

class Sell : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        var item_value:Transaction= intent.getSerializableExtra("stock_data") as Transaction
        Log.i("value",item_value.toString())
        sellCompany.text=item_value.company.toString()
        sell_qty.text=item_value.count.toString()
//        var x:Double=sell_qty.text.toString().toDouble()
        sell_btn.setOnClickListener {
            var uid = FirebaseAuth . getInstance () . uid ?: ""




//           set quantity and Price to Transaction ,
//           Create JSON TRansaction object and Create a SQL QUERY
// and  call Thread(runnable).start()


            var json:JSONObject= JSONObject()
            json.put("uid",item_value.uid)
            json.put("company",item_value.company)
            json.put("count",item_value.count)
            json.put("price",item_value.price)

            val runnable = Runnable {
//                details/jkcFD1klHzYaWtRHMEeh7zT9PS63
                val url = "http://finalproject.com/delete"
                var r=MyUtility.sendHttPostRequest(url, json.toString())
                Log.i("r value",r.toString())
                sell_text.text="Succesfully done"
                /*if(r==true.toString())
                {
                    sell_text.text="Succesfull"
                }
                else
                {
                    sell_text.text="Cannot be done"
                }*/
            }
            Thread(runnable).start()


        }
    }

}
