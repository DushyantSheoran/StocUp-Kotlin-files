package com.example.saisekhar.finalproject1


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_purchase.*
import kotlinx.android.synthetic.main.fragment_addmoney.*
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class addmoney : Fragment() {
    companion object {
        fun newInstance():addmoney
        {
            addmoney().apply {
            }
            return addmoney()
        }
    }
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }
    var uid = FirebaseAuth . getInstance () . uid ?: ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addmoney, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_btn.setOnClickListener{
            var p=add_qty.text.toString().toDouble()
            var json: JSONObject = JSONObject()
            var url1="http://finalproject.com/add_money"
            json.put("uid",uid)
            json.put("amount",p)
            val runnable = Runnable {
                var url="http://finalproject.com/add_money"
                val r=MyUtility.sendHttPostRequest(url, json.toString())

            }
            Thread(runnable).start()
        }
    }


}
