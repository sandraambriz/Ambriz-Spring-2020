package edu.boisestate.FinalProject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_login.*
import kotlinx.android.synthetic.main.user_registration.*
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Handles initial page seen by user with Login and Register buttons.
 */
class MainActivity : AppCompatActivity() {

    //handler that allows us to access the user database functions
    lateinit var userHandler:UserDatabaseHelper
    //lateinit var session:SessionManager


    //Starting the first page of app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Project 3 Stuff - start
        val apiHost:String = BuildConfig.WEB_SERVER + "/api/v1/"

        if(BuildConfig.BUILD_TYPE == "debug"){
            main_debug_button.visibility = View.GONE
        }

        //Project 3 Stuff - end


        //session = SessionManager(applicationContext)
        //initializing our handler
        userHandler= UserDatabaseHelper(this)

        //Initially, display Login and Register button page.
        showRegistrationLoginPage()

        //If Register is clicked, display registration (user_registration.xml) page
        main_registerButton.setOnClickListener {
            showRegistrationPage()
        }

        //If Login button is clicked, display login (user_login.xml) page.
        main_loginButton.setOnClickListener {
            showLoginPage()
        }

        //In Register page, if user clicks Save button, then add them to database
        register_saveButton.setOnClickListener {

            //Getting values from user input fields
            val username = register_name.text.toString()
            val email = register_email.text.toString()
            val password = register_password.text.toString()

            //Checking that the user provided input for all fields
            if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                //Checking that the user DOES not exist, then adding them to the database
                //Then show them login page
                if(!(userHandler.userAlreadyExists(email)) && validateEmail(email)){
                    userHandler.addUser(username, email, password)
                    Toast.makeText(this, "You have registered!", Toast.LENGTH_LONG).show()
                    showLoginPage()
                }else if(!(validateEmail(email))){
                    Toast.makeText(this, "Invalid Email!", Toast.LENGTH_LONG).show()
                }
            }else if(username.isNotEmpty() && email.isNotEmpty() && password.isEmpty()){
                Toast.makeText(this, "Please provide a password",
                    Toast.LENGTH_SHORT).show()
            }else if(username.isNotEmpty() && email.isEmpty() && password.isNotEmpty()){
                Toast.makeText(this, "Please provide an email",
                    Toast.LENGTH_SHORT).show()
            }else if(username.isEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Please provide a username",
                    Toast.LENGTH_SHORT).show()
            }
        }

        //In Login page, if the user clicks Login, check if the user exists and log them in
        login_button.setOnClickListener {
            val email = login_email.text.toString()
            val password = login_password.text.toString()

            //Checking that the user has provided input for all fields
            if(email.isNotEmpty() && password.isNotEmpty()){
                //Checking that the user is in the database, then take them to homepage
                if(userHandler.userExists(email, password)){

                    //session.loginSessionStart(email, password)

                    val userHomepageIntent = Intent(this, UserHomepage::class.java)
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    startActivity(userHomepageIntent)
                    //finish()
                }
            }else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(this, "Please provide an email and password",
                        Toast.LENGTH_SHORT).show()
            }else if(email.isEmpty() && password.isNotEmpty()){
                Toast.makeText(this, "Please provide an email",
                    Toast.LENGTH_SHORT).show()
            }else if(email.isNotEmpty() && password.isEmpty()){
                Toast.makeText(this, "Please provide a password",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }

    /**
     * Shows user registration page.
     */
    private fun showRegistrationPage(){
        register_layout.visibility= View.VISIBLE
        login_layout.visibility= View.GONE
        registration_login_layout.visibility= View.GONE
    }

    /**
     * Shows user login page.
     */
    private fun showLoginPage(){
        register_layout.visibility= View.GONE
        login_layout.visibility= View.VISIBLE
        registration_login_layout.visibility= View.GONE
    }

    /**
     * Shows registration and login page.
     */
    private fun showRegistrationLoginPage(){
        register_layout.visibility= View.GONE
        login_layout.visibility= View.GONE
        registration_login_layout.visibility= View.VISIBLE
    }


    /**
     * Ensures that the email field matches email format.
     */
    fun validateEmail(e:String):Boolean{
        val regex:String = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern:Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher:Matcher = pattern.matcher(e)
        return matcher.matches()
    }

}