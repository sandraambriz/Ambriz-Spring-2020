package edu.boisestate.Project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Handles the information seen by the user when they enter their home
 * after successfully logging in.
 */
class UserHomepage : AppCompatActivity() {

    /**
     * When activity is created display a bottom navigation bar,
     * add product button, and view user list button.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_homepage)
    }

}
