package com.example.dsheoran.finalproject1

import android.os.AsyncTask
import android.os.Build
import android.os.SystemClock
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.cardview_trans.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference

public class Trans()

public class DetailRecyclerAdapter(val context: Details) : RecyclerView.Adapter<DetailRecyclerAdapter.MyViewHolder>() {
    var trans=ArrayList<Transaction>()
    init{
        var uid = FirebaseAuth . getInstance () . uid ?: ""
        Log.i("uid in Detail Fragment",uid.toString())
        var url="http://finalproject.com/uid/"+uid
        var task=DownTrans(trans)
        task.execute(url)

    }
    interface detailsInterface
    {
        fun setAction(trns:Transaction)
    }
    lateinit var mlistner:detailsInterface
    fun setListner(listner: detailsInterface)
    {
        mlistner=listner
    }
    fun changeTrans(trn: Transaction)
    {
        var i=0;
        var pos:Int=-1
        while (i<trans.size)
        {
            if(trans[i]==trn)
            {
                pos=i
            }
            i++
        }
        trans.remove(trn)
        notifyItemRemoved(pos)

    }

    inner class DownTrans(down:ArrayList<Transaction>):AsyncTask<String,Void,String>()
    {
        val weakData = WeakReference<ArrayList<Transaction>>(down)
        override fun doInBackground(vararg params: String?): String? {
            var res = MyUtility.downloadJSONusingHTTPGetRequest(params[0]!!)
            Log.i("download result ", res.toString())
            if(res!=null)
            {
                return res!!
            }
            else
            {
                return null
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.i("onPosExecute",result.toString())
            if (result != null) {
                result.trimIndent()
                var list = weakData.get()
                if (list != null)
                {
                    list.clear()
                    val arr = JSONArray(result)
                    var i: Int = 0;
                    while (i < arr.length())
                    {
                        Log.i("each array value",arr.getJSONObject(i).toString())
                        val job: JSONObject = arr.getJSONObject(i)
                        val mov=Transaction(job.getString("uid"),
                                job.getString("company"),
                                job.getDouble("count"),
                                job.getDouble("price"))
                        list.add(mov)
                        i++
                    }
                    Log.i("listsize",list.toString())
                    notifyDataSetChanged();
                }
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DetailRecyclerAdapter.MyViewHolder {
        val v: View
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.cardview_trans, p0, false))
    }

    override fun getItemCount(): Int {
        return trans.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.comp_name!!.text=trans[p1].company
        p0.comp_count!!.text=trans[p1].count.toString()
        p0.comp_price!!.text=trans[p1].price.toString()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var comp_name: TextView? =itemView.trans_compaany
        var comp_count=itemView.trans_count
        var comp_price=itemView.tran_price
        init {
            itemView.setOnClickListener{
                if(mlistner!=null)
                {
                    if(adapterPosition!=RecyclerView.NO_POSITION)
                    {
                        Log.i("inside Stocks Adapter","working")
                        mlistner.setAction(trans[adapterPosition])
                    }
                }

            }
        }

    }
}
