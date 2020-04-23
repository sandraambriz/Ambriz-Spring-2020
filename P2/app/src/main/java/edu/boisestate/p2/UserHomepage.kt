package edu.boisestate.p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_user_homepage.*
import kotlinx.android.synthetic.main.fragment_home.*

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
