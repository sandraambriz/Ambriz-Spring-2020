package edu.boisestate.p2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_user_homepage.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class UserProductsList : AppCompatActivity() {

    lateinit var productHandler: ProductDatabaseHelper

    //Used to handle the bottom navigation bar item clicks
    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            //if home image on nav bar is clicked, then display home page
            //if home is clicked, take user to homepage intent
            when (item.itemId) {
                R.id.home -> {
                    println("home clicked")
                    //val homepageIntent = Intent(this, UserHomepage::class.java)
                    //startActivity(homepageIntent)
                    val homeFragmentTransaction = supportFragmentManager.beginTransaction()
                    homeFragmentTransaction.replace(R.id.fragmentContainer, UserHomeFragment())
                    homeFragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                //if api product list is clicked, then display the api list
                R.id.products -> {
                    println("products clicked")
                    val recyclerViewListIntent = Intent(this, MakeupAPIConsumeData::class.java)
                    startActivity(recyclerViewListIntent)
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, MakeupAPIListFragment())
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        //Enables listening for navigation bar items
        bottomNavigationBar.setOnNavigationItemSelectedListener(navItemSelectedListener)

        productHandler = ProductDatabaseHelper(this)
        val productData = productHandler.getProducts()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productData)
        productlist_listview.adapter = adapter

        productlist_listview.setOnItemClickListener { parent, view, position, id ->
            val item = adapter.getItem(position).toString()

            //if delete button is clicked, then delete that item
            productlist_deleteButton.setOnClickListener {
                adapter.remove(item)
                productHandler.deleteProduct(item)
                adapter.notifyDataSetChanged()
                Log.d("DELETE()", productHandler.getProducts().toString())
            }

            //if update button is clicked, then update that item
            //TODO figure this out
            productlist_updateButton.setOnClickListener {
                val edit = productlist_editProduct.text.toString()
                adapter.remove(item)
                adapter.insert(edit, position)
                productHandler.updateProduct(item)
                Log.d("UPDATE()", productHandler.getProducts().toString())
            }
        }
    }

}
