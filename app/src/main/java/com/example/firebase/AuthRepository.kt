package com.example.firebase

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepository(private val activity : Activity) {
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun launchregister(email: String, password: String){
        registerUser(email, password)
        loginUser(email, password)
    }

    suspend fun registerUser(email: String, password: String)  = suspendCoroutine<Unit> {
        val currentUser = auth.currentUser
        if(currentUser != null){
            Log.d("Tag", "registerSuccess")

        }else
        {            Log.d("Tag", "registccess")
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Tag", "createUserWithEmail:success")
                    val user = auth.currentUser
                    it.resume(Unit)


                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Tag", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(activity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
                it.resume(Unit)

            }.addOnFailureListener {
                it.printStackTrace()
            }
    }
    suspend fun loginUser(email: String, password: String) = suspendCoroutine<Unit> {
        Log.d("Tag", "LoginFunc")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "You are logged in.",
                        Toast.LENGTH_SHORT).show()
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Tag", "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    it.resume(Unit)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Tag", "signInWithEmail:failure", task.exception)
                    Toast.makeText(activity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }
}