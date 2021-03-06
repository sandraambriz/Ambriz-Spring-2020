package edu.boisestate.FinalProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_user_product_list.*

class UserProductsList : AppCompatActivity() {

    lateinit var productHandler: ProductDatabaseHelper
    //lateinit var session:SessionManager

    //Used to handle the bottom navigation bar item clicks
    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            //if home image on nav bar is clicked, then display home page
            //if home is clicked, take user to homepage intent
            when (item.itemId) {
                R.id.home -> {
                    val homepageIntent = Intent(this, UserHomepage::class.java)
                    startActivity(homepageIntent)
                    finish()
                    return@OnNavigationItemSelectedListener true
                }
                //if api product list is clicked, then display the api list
                R.id.popularProducts -> {
                    val recyclerViewListIntent = Intent(this, MakeupAPIConsumeData::class.java)
                    startActivity(recyclerViewListIntent)
                    finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.recommendedProducts ->{
                    val recommendedProductIntent = Intent(this, RecommendedJSONConsumeData::class.java)
                    startActivity(recommendedProductIntent)
                    finish()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.logout ->{
                    val mainPageIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainPageIntent)
                    finish()
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_product_list)


        //Enables listening for navigation bar items
        userlist_bottomNavigationBar.setOnNavigationItemSelectedListener(navItemSelectedListener)

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
            }

            //if update button is clicked, then update that item
            productlist_updateButton.setOnClickListener {
                val edit = productlist_editProduct.text.toString()
                adapter.remove(item)
                adapter.insert(edit, position)
            }
        }
    }

}
