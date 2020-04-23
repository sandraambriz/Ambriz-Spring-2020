package edu.boisestate.p2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.boisestate.p2.R.id.fragmentContainer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Fragment for displaying home from bottom navigation bar.
 * TODO carry over logic from UserHomepage activity.
 */
class UserHomeFragment : Fragment() {

    //used to handle the bottom navigation bar clicks
    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            //if home image on nav bar is clicked, then display home page
            //if home is clicked, take user to homepage intent
            when (item.itemId) {
                R.id.home -> {
                    println("home clicked")
                    val homepageIntent = Intent(activity, UserHomepage::class.java)
                    startActivity(homepageIntent)
                    return@OnNavigationItemSelectedListener true
                }
                //if api product list is clicked, then display the api list
                R.id.products -> {
                    println("products clicked")
                    val recyclerViewListIntent = Intent(activity, MakeupAPIConsumeData::class.java)
                    startActivity(recyclerViewListIntent)
                    val transaction = fragmentManager!!.beginTransaction()
                    transaction.replace(fragmentContainer, MakeupAPIListFragment())
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        }

    /**
     * Defines fragment when created.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
        lateinit var productHandler:ProductDatabaseHelper
        v.bottomNavigationBar.setOnNavigationItemSelectedListener(navItemSelectedListener)

        //our product database handler
        productHandler = ProductDatabaseHelper(activity!!)


        //If add product is clicked, then add that product to database
        v.homepage_addProductButton.setOnClickListener {
            val productName: String = v.homepage_addProductEditText.text.toString()
            productHandler.addProduct(productName)
            Log.d("ADD PRODUCT", productHandler.getProducts().toString())
        }

        //if view products button is clicked,
        //then take user to their own product list and allow delete and update
        v.homepage_viewUserProductsButton.setOnClickListener {
            val listProductsIntent = Intent(activity, UserProductsList::class.java)
            activity?.startActivity(listProductsIntent)
        }
        return v
    }

}
