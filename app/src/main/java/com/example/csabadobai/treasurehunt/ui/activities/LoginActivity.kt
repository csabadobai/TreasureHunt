package com.example.csabadobai.treasurehunt.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.csabadobai.treasurehunt.R
import com.example.csabadobai.treasurehunt.net.commands.UserLoginCommand
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInBtn = signInView
        val postBtn = signUpView

        signInBtn.setOnClickListener({
            doAsync {
                val username = usernameView.text.toString()
                val password = passwordView.text.toString()
                val userLogin = UserLoginCommand(username, password).execute()
                if (userLogin.isLoginSuccess) {
                    uiThread {
                        val intent = Intent(ctx, HuntsActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    val message = userLogin.reason
                    uiThread {
                        alert(message) {
                            noButton {}
                        }.show()
                    }
                }
            }
        })

        postBtn.setOnClickListener({
            val intent = Intent(ctx, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}
