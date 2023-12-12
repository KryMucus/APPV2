package com.example.footballapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.footballapplication.databinding.ActivityMainBinding
import com.example.footballapplication.databinding.FragmentLoginBinding
import com.example.footballapplication.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {






    private lateinit var binding: ActivityMainBinding




    private fun initSharedPreferences() {

        // getSharedPreferences crée la préférence si elle nexiste pas
        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        //initialisation du statut de connexion = faux
        editor.putBoolean("userIsLoggedIn", false).apply()

        //pour l'instant on crée les SharedPreferences à la main
        editor.putString("username", "Christophe_Mae")
        editor.putString("password", "Willemite")
        editor.apply()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val userIsLoggedIn = sharedPref.getBoolean("userIsLoggedIn", false)

        //on initialise les shared Preferences ;
        initSharedPreferences()

        setContentView(R.layout.activity_main)


        if (userIsLoggedIn) {

            //Chargement de la page principale
            setupMainContent()

            Log.i("FootBall APP", "Utilisateur Connecté")
            Log.i("FootBall APP", "Chargement de la home page")
        } else {

            //Formulaire de connection
            showLoginFragment()
            Log.i("FootBall APP", "Utilisateur Non Connecté")
            Log.i("FootBall APP", "Chargement de la page d'accueil")
        }

    }



    private fun setupMainContent() {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Configuration de la navigation pour que BottomNavigationView interagisse avec le NavController
        navView.setupWithNavController(navController)
    }

    private fun showLoginFragment() {

        // Obtenir une référence au NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)

        // Masquer le NavHostFragment
        navHostFragment?.view?.visibility = View.GONE

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()
    }

}
