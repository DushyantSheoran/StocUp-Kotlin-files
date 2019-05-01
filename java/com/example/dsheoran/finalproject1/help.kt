package com.example.dsheoran.finalproject1

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.content_help.*
import java.io.IOException
import java.io.InputStream
//import jdk.nashorn.internal.objects.ArrayBufferView.buffer



class help : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        setSupportActionBar(toolbar)
//        helptext.text=""
//        var str1=""
//        StreamReader sr = new StreamReader (assets.Open ("read_asset.txt"))
        var p: InputStream? =null
        p=assets.open("text.txt")
        var sz=p.available()
        var buf:ByteArray= ByteArray(sz)
        p.read(buf)
        p.close()
        val str1 = String(buf)
        helptext.text=str1

        /*try{
            var p:InputStream = assets.open("text.txt")
            var sz=p.available()
            var buf:ByteArray= ByteArray(sz)
            p.read(buf)
            p.close()
            str1=buf.toString()
            helptext.text=str1


        }catch (ex: IOException)
        {
            ex.printStackTrace()
        }*/
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
