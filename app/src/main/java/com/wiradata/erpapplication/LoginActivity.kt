package com.wiradata.erpapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wiradata.erpapplication.service.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            AuthService(this).login(
                loginUsernameEditText.text.toString(),
                loginPasswordEditText.text.toString()
            )
        }
    }
}