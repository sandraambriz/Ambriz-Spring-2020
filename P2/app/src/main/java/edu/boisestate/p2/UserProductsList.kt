package edu.boisestate.p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_user_homepage.*

class UserProductsList : AppCompatActivity() {

    lateinit var productHandler: ProductDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

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
