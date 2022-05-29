package com.wiradata.erpapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wiradata.erpapplication.service.AuthService
import com.wiradata.erpapplication.users.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private fun formIsValid() : Boolean {
        var username: String = loginUsernameEditText.text.toString()
        var password: String = loginPasswordEditText.text.toString()

        if (username.isEmpty()) {
            loginUsernameEditText.error = "Please supply valid username"
            return false
        }

        if (password.isEmpty()) {
            loginPasswordEditText.error = "Please supply valid password"
            return false
        }

        return true
    }

    private fun failed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Attention")
        builder.setMessage("Invalid username or password. Please supply valid username and password.")
        builder.setPositiveButton("OK") { dialog, which ->
            loginUsernameEditText.setText("")
            loginPasswordEditText.setText("")
        }
        builder.show()
    }

    private fun success(authObj: LoginResponse ){
        Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
        val mPrefs: SharedPreferences = getSharedPreferences("MyMode", 0)
        val prefsEditor: SharedPreferences.Editor = mPrefs.edit()
        val myGson: Gson =  Gson()
        val myJson: String = myGson.toJson(authObj)
        Log.i("jacky", myJson)
        prefsEditor.putString("AuthObj", myJson)
        prefsEditor.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
           if (formIsValid()) {
               var authObj: LoginResponse =  AuthService(this).login(
                   loginUsernameEditText.text.toString(),
                   loginPasswordEditText.text.toString()
               )

               if (authObj.token.isEmpty()) failed()
               else {
                   success(authObj)
                   val mainIntent = Intent(this, MainActivity::class.java)
                   startActivity(mainIntent)
               }
           }
        }
    }
}