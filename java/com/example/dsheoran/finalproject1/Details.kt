package com.example.dsheoran.finalproject1


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dsheoran.finalproject1.R.id.portfolio_value
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Details : Fragment(), DetailRecyclerAdapter.detailsInterface {
    override fun setAction(trns: Transaction) {
        /*recyclerAdapter.changeTrans(trns)
        var uid = FirebaseAuth . getInstance () . uid ?: ""
        var c1=par1.text.toString().toDouble()
        var c2=par2.text.toString().toDouble()

        c1=c1+(trns.count!!* trns.price!!)
        c2=c2-(trns.count!!*trns.price!!)
        par1.text=c1.toString()
        par1.text=c2.toString()

        val json = JSONObject()
        json.put("UID",uid)
        json.put("Company",trns.company)*/

        listner.function(trns)

    }

    companion object {
        fun newInstance():Details
        {
            Details().apply {

            }
            return Details()
        }
    }
    lateinit var listner:DetailTrs
    lateinit var recyclerView : RecyclerView
    lateinit var recyclerAdapter: DetailRecyclerAdapter
    interface DetailTrs
    {
        fun function(tp:Transaction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.i("Inside Detail","Fragment")

        val rootView= inflater.inflate(R.layout.fragment_details, container, false)
        recyclerView= rootView.findViewById(R.id.recViewDetails)
        par1=rootView.findViewById(R.id.portfolio_value)
        par2=rootView.findViewById(R.id.buying_power)

        var uid = FirebaseAuth . getInstance () . uid ?: ""
        Log.i("uid in Detail Fragment",uid.toString())
        var url="http://10.1.1.205/details/"+uid
        var task=getBuyPower(par1,par2)
        task.execute(url)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.setLayoutManager(mLayoutManager)

        recyclerAdapter= DetailRecyclerAdapter(Details())
        recyclerView.adapter=recyclerAdapter;
        recyclerAdapter.setListner(this)

        recyclerView.getItemAnimator()!!.setAddDuration(2000);
        recyclerView.getItemAnimator()!!.setRemoveDuration(2000)
        recyclerView.getItemAnimator()!!.setMoveDuration(1500);
        recyclerView.getItemAnimator()!!.setChangeDuration(2000);



        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is DetailTrs)
        {
            listner=context
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    init {
        /*var uid = FirebaseAuth . getInstance () . uid ?: ""
        Log.i("uid in Detail Fragment",uid.toString())
        var url="http://finalproject.com/details/"+uid
        val money:value
        money= value(0.0,0.0)
        var task=getBuyPower(money)
        task.execute(url)*/

    }
    lateinit var par1: TextView
    lateinit var par2: TextView
    val money:value= value()
    inner class getBuyPower(mon:TextView,mon1:TextView):AsyncTask<String,Void,String>()
    {
        val weakData1 = WeakReference<TextView>(mon)
        val weakData2 = WeakReference<TextView>(mon1)

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
            if (result != null) {
                result.trimIndent()
                var l1 = weakData1.get()
                var l2=weakData2.get()
                var job: JSONObject = JSONObject(result)
                Log.i("value in string",job.toString())
                Log.i("value Avail amount",job.getDouble("Avail_amount").toString())
                Log.i("value Spent_amount",job.getDouble("Spent_amount").toString())
//                var pq=job.getDouble("Avail_amount")
//                val pr=job.getBoolean("Spent_amount")
//                var mov = value(job.getDouble("portfo"), job.getDouble("buy_power"))
                l1!!.text=job.getDouble("Avail_amount").toString()
                l2!!.text=job.getDouble("Spent_amount").toString()


            }
        }

    }


}
