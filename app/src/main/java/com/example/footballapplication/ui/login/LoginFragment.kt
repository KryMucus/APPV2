package com.example.footballapplication.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.footballapplication.MainActivity
import com.example.footballapplication.R
import com.example.footballapplication.databinding.FragmentLoginBinding
import com.example.footballapplication.ui.home.HomeViewModel

//Attention l'indentation compte

//définit la classe LoginFragment qui hérite de fragment
class LoginFragment : Fragment() {

    //binding est utilisée pour accéder aux vues dans le
    //Layout de ce fragment via la liason de données

    //Binding est  initialisé dans les onCreateView et
    //set à nul dans les onDestroyView
    private var _binding: FragmentLoginBinding? = null

    // les !! Permettent de guarantir que binding soit non nul
    private val binding get() =  _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialisation de binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //listener pour le bouton de connexion :

        binding.loginBtn.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            performLogin(username,password)
        }
        return root
    }

    // à revoir car il faudra comparer les identifiants du sign IN et du Login
    //Pour l'instant on compare le LOgin et le LOgin donc toujours vrai
    private fun performLogin(username : String, password: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword =  sharedPreferences.getString("password", "")

        if (username == savedUsername && password == savedPassword) {
            //Connexion réussie :
            Toast.makeText(activity, "Connexion réussie",Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().putBoolean("userIsLoggedIn", true).apply()

            Log.i("FootBall APP", "Statut de connexion : " +sharedPreferences.getBoolean("userIsLoggedIn",false) )

            //On relance l'app pour charger le OnCreate du main pour charger la page principale :

            findNavController().navigate(R.id.action_loginFragment_to_destination)

        }else{
            //Connexion Echouée :
            Toast.makeText(activity, "Connexion Echouée",Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().putBoolean("userIsLoggedIn", false).apply()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


























