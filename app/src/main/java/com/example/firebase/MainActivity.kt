package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val manager: FragmentManager = supportFragmentManager
//        val transaction: FragmentTransaction = manager.beginTransaction()
//        transaction.add(R.id.fragmentHolder,RegisterFragment.newInstance(),"Register")
//        transaction.addToBackStack(null)
//        transaction.commit()
        auth = Firebase.auth
        registerUser("aaaa@gmail.com", "aaaa121fsdfs")
    }

    fun registerUser(email: String, password: String){
        val currentUser = auth.currentUser
        if(currentUser != null){
            Log.d("Tag", "registerSuccess")

        }else
        {            Log.d("Tag", "registccess")
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Tag", "createUserWithEmail:success")
                    val user = auth.currentUser


                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Tag", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

            }.addOnFailureListener {
                it.printStackTrace()
            }
    }
}