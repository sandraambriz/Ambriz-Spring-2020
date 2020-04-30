package edu.boisestate.FinalProject


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.boisestate.FinalProject.R.id.fragmentContainer
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Fragment for displaying home from bottom navigation bar.
 * TODO carry over logic from UserHomepage activity.
 */
class UserHomeFragment : Fragment() {

    lateinit var session:SessionManager

    //used to handle the bottom navigation bar clicks
    private val navItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            //if home image on nav bar is clicked, then display home page
            //if home is clicked, take user to homepage intent
            when (item.itemId) {
                R.id.home -> {
                    val homepageIntent = Intent(activity, UserHomepage::class.java)
                    startActivity(homepageIntent)
                    return@OnNavigationItemSelectedListener true
                }
                //if api product list is clicked, then display the api list
                R.id.popularProducts -> {
                    val recyclerViewListIntent = Intent(activity, MakeupAPIConsumeData::class.java)
                    startActivity(recyclerViewListIntent)
                    val transaction = fragmentManager!!.beginTransaction()
                    transaction.replace(fragmentContainer, MakeupAPIListFragment())
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                //if recommended is clicked, then show recommended list
                R.id.recommendedProducts ->{
                    val recommendedProductIntent = Intent(activity, RecommendedJSONConsumeData::class.java)
                    startActivity(recommendedProductIntent)
                    return@OnNavigationItemSelectedListener true
                }
                //if logout is clicked, then take user back to homepage
                R.id.logout ->{
                    session.logout()
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

        session = SessionManager(context!!)

        val v: View = inflater.inflate(R.layout.fragment_home, container, false)
        lateinit var productHandler:ProductDatabaseHelper
        v.userlist_bottomNavigationBar.setOnNavigationItemSelectedListener(navItemSelectedListener)

        //our product database handler
        productHandler = ProductDatabaseHelper(activity!!)

            //If add product is clicked, then add that product to database
            v.homepage_addProductButton.setOnClickListener {
                val productName: String = v.homepage_productNameEditText.text.toString()
                val productType: String = v.homepage_productTypeEditText.text.toString()
                val productBrand: String = v.homepage_productBrandEditText.text.toString()
                val locationOfPurchase: String = v.homepage_locationOfPurchaseEditExt.text.toString()
                val storeName: String = v.homepage_storeNameEditText.text.toString()
                val purchaseDate: String = v.homepage_purchaseDateEditText.text.toString()
                val expirationDate: String = v.homepage_expirationDateEditText.text.toString()

                val userProduct:UserProduct = UserProduct(productName,
                    productType, productBrand, locationOfPurchase, storeName, purchaseDate, expirationDate)

                if(productName.isEmpty() || productType.isEmpty() || productBrand.isEmpty()
                    || locationOfPurchase.isEmpty() || storeName.isEmpty() || purchaseDate.isEmpty()){
                    Toast.makeText(activity, "Please provide input for all fields",
                        Toast.LENGTH_SHORT).show()
                }else if(productName.isNotEmpty() && productType.isNotEmpty() && productBrand.isNotEmpty()
                    && locationOfPurchase.isNotEmpty() && storeName.isNotEmpty() && purchaseDate.isNotEmpty()){
                    productHandler.addProduct(userProduct)
                    Toast.makeText(activity, "Success! Product is added.",
                        Toast.LENGTH_SHORT).show()
                }

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
