package com.example.dsheoran.finalproject1

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,Details.DetailTrs {
    override fun function(tp: Transaction) {
        var intent:Intent= Intent(this,Sell::class.java)
        intent.putExtra("stock_data",tp)
        startActivity(intent)
    }
    var uid:String?=null
    var person:Person?=null
    val getVal = object : ValueEventListener
    {
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            person= p0.getValue<Person>(Person::class.java)
            person_name.text=person!!.firstname+" "+person!!.lastname
            person_mail.text=person!!.email
            Picasso.get().load(person!!.imgurl).fit().into ( person_img)
//            Log.i("person",person.toString())
//                per= JSONObject(person)
//            Log.i("person",person!!.username.toString())
//            person_name.text=person!!.username
//            person_mail.text=person!!.useremail
//            Picasso.get().load(person!!.profileImageUrl ).fit().into ( mypic )

        }

    }

    init {
        uid = FirebaseAuth . getInstance () . uid ?: ""
        val mDatabase : DatabaseReference = FirebaseDatabase.getInstance().reference
        val mRef = mDatabase.child("users").child(uid!!)
        mRef.addValueEventListener(getVal)
    }


    lateinit var fragment : Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        var toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar (findViewById(R.id.toolbar))
        val appBar = supportActionBar



        val fm = supportFragmentManager
        fragment = Details.newInstance()
        Log.i("abc","asdfghjklqwertyuiop")
        val fT = fm.beginTransaction();

        fT.replace(R.id.dummy,fragment)
        // fT.addToBackStack("aboutme container")
        fT.commit()

        appBar!!.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.signout-> {
                // Handle the camera action
                var intent2= Intent(this,LoginActivity::class.java)
                intent . flags = Intent . FLAG_ACTIVITY_CLEAR_TASK .or( Intent . FLAG_ACTIVITY_NEW_TASK )
                startActivity ( intent2 )
            }
            R.id.banking -> {
                var intent=Intent(this,BankingActivity::class.java)
                startActivity(intent)

            }
            R.id.market -> {
                var intent2= Intent(this,Markets::class.java)
                intent . flags = Intent . FLAG_ACTIVITY_CLEAR_TASK .or( Intent . FLAG_ACTIVITY_NEW_TASK )
                startActivity ( intent2 )

            }
            /*R.id.scheduler-> {

            }
            R.id.setting -> {

            }*/
            R.id.help -> {
                var intent = Intent(this,help::class.java)
                startActivity(intent)

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
