package com.example.dsheoran.finalproject1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(),LoginFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener,BlankFragment.BlankFragmentInterface {
    override fun Login() {
        var intent:Intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    lateinit var frag : Fragment
    override fun onClickButton(id: Int) {
        if(id==R.id.register)
        {
            frag=SignupFragment . newInstance ("" , "" )
            supportFragmentManager . beginTransaction () . replace (R.id.login_container, frag). commit ()
        }
        else if(id==R.id.signin)
        {
            frag=LoginFragment ()
            supportFragmentManager . beginTransaction () . replace (R.id.login_container,frag).commit ()
        }
    }


    var auth : FirebaseAuth? = null
    override fun onSignUpRoutine(email: String, passwd: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        frag=SignupFragment . newInstance (email , passwd )
        supportFragmentManager . beginTransaction () . replace (R.id.login_container,frag ). commit ()
    }

    override fun onSignInRoutine() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        frag=LoginFragment()
        supportFragmentManager . beginTransaction () . replace (R.id.login_container,frag).commit ()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(savedInstanceState==null)
        {
            frag= BlankFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.login_container ,frag). commit ()
        }
        else
        {
            Log.i("rotate","changed")
            frag=supportFragmentManager.getFragment(savedInstanceState,"frag")!!
            supportFragmentManager.beginTransaction().add(R.id.login_container ,frag ). commit ()

        }
//        supportFragmentManager.beginTransaction().add(R.id.login_container ,BlankFragment.newInstance() ). commit ()

    }
    public override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState!!,"frag",frag)
    }
}
