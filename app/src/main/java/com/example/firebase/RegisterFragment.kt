package com.example.firebase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebase.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterFragment : Fragment() {


    //UserDB
    lateinit var repository : AuthRepository
    private lateinit var binding: FragmentRegisterBinding

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        binding.register.setOnClickListener(){
            //Todo textvalidation
            var email = binding.editTextTextEmailAddress.text.toString()

            var password = binding.editTextTextPassword.text.toString()
            registerUser(email,password)

        }
        return(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        repository = AuthRepository(requireActivity())

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    // TODO: Use the ViewModel
    }

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun registerUser(email : String, password : String){
        uiScope.launch{
            withContext(Dispatchers.IO){
                repository.launchregister(email, password)

            }
        }
    }

}