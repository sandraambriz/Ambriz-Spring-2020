package edu.boisestate.p2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.makeupapi_product_row.view.*

/**
 * Manages the view of the products as they appear on screen.
 */
class ConsumeDataAdapter(val base:List<Base>): RecyclerView.Adapter<CustomViewHolder>(){


    /**
     * Creates the view and calls the list layout (makeupapi_product_row)
     * and customizes.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val row = layoutInflater.inflate(R.layout.makeupapi_product_row, parent, false)
        return CustomViewHolder(row)
    }

    /**
     * On position, display the desired variables of api product.
     */
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val productTitle = productTitles.get(position)
        val product = base.get(position)
        holder?.itemView?.api_product_name?.text = product.name
        holder?.itemView?.api_prouct_brand?.text = product.brand
        holder?.itemView?.api_product_desc?.text = product.description
        holder?.itemView?.api_product_type?.text = product.product_type

        //holder.itemView.api_product_colors.text = product.product_colors.toString()
        val producImage = holder?.itemView?.api_featured_image
        Picasso.with(holder?.itemView?.context).load(product.image_link).into(producImage)

    }

    /**
     * Gets number of items from api list.
     */
    override fun getItemCount(): Int {
        return base.size
    }


}

/**
 * Recycler view class needed to customize view.
 */
class CustomViewHolder(view : View):RecyclerView.ViewHolder(view){}