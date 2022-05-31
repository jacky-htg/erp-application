package com.wiradata.erpapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private fun loginPage() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mPrefs: SharedPreferences = getSharedPreferences("MyMode", 0)
        if (mPrefs.getString("authToken", "").toString().isEmpty()) {
           loginPage()
        } else {
            welcomeText.setText("Welcome " + mPrefs.getString("authUserName", "Dude").toString())
            btnLogout.setOnClickListener {
                mPrefs.edit().clear().commit()
                loginPage()
            }
        }
    }
}