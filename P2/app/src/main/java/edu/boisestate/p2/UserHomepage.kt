package edu.boisestate.p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_user_homepage.*

/**
 * Handles the information seen by the user when they enter their home
 * after successfully logging in.
 */
class UserHomepage : AppCompatActivity() {

    //handler that gives us access to our product database helper class
    lateinit var productHandler:ProductDatabaseHelper

    //handles the bottom navigation bar clicks
    private val navItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item->
        //if home image on nav bar is clicked, then display home page
        //TODO ensure that user homepage is displayed
        when(item.itemId){
            R.id.home->{
                println("home clicked")
                changeFragment(UserHomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            //if api product list is clicked, then display the api list
            R.id.products->{
                println("products clicked")
                val recyclerViewListIntent = Intent(this, MakeupAPIConsumeData::class.java)
                startActivity(recyclerViewListIntent)
                changeFragment(MakeupAPIListFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * When activity is created display a bottom navigation bar,
     * add product button, and view user list button.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //display homepage layout
        setContentView(R.layout.activity_user_homepage)

        //ensure that the bottom navigation bar can be clicked, be on home fragment
        bottomNavigationBar.setOnNavigationItemSelectedListener(navItemSelectedListener)
        changeFragment(UserHomeFragment())

        //our product database handler
        productHandler = ProductDatabaseHelper(this)

        //if add button is clicked
        //get content from text field,
        //and ad it to the database
        homepage_addProductButton.setOnClickListener {
            val productName:String = homepage_addProductEditText.text.toString()
            productHandler.addProduct(productName)
            Log.d("ADD PRODUCT", productHandler.getProducts().toString())
        }

        //if view products button is clicked,
        //then take user to their own product list and allow delete and update
        homepage_viewUserProductsButton.setOnClickListener {
            val listProductsIntent = Intent(this, UserProductsList::class.java)
            startActivity(listProductsIntent)
        }

    }

    /**
     * Changes the fragments on click.
     */
    private fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}
