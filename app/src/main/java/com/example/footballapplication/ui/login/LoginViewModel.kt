package com.example.footballapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class LoginViewModel : ViewModel(){
    private val _text = MutableLiveData<String>().apply{
        value = "Ici on mettra des trucs relatifs à la connexion"
    }
    //appel à la fonction _text et on stock dans la variable text
    val text: LiveData<String> = _text

}