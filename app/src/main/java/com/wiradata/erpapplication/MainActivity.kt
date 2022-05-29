package com.wiradata.erpapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.wiradata.erpapplication.entity.AuthObj
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
        val gson = Gson()
        val json: String = mPrefs.getString("AuthObj", "").toString()
        val authObj: AuthObj = gson.fromJson(json, AuthObj::class.java)
        if (json.isEmpty()) {
           loginPage()
        } else {
            welcomeText.setText("Welcome " + authObj.user_.name_)
            btnLogout.setOnClickListener {
                mPrefs.edit().clear().commit()
                loginPage()
            }
        }
    }
}