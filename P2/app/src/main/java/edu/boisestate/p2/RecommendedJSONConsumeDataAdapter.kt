package edu.boisestate.p2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recommended_product_row.view.*

class RecommendedJSONConsumeDataAdapter (val productBase:List<RecommendedBase>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val row = layoutInflater.inflate(R.layout.recommended_product_row, parent, false)
        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return productBase.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val recommended = productBase.get(position)
        holder?.itemView.recommend_productName?.text = recommended.name
        holder?.itemView.recommended_productType?.text = recommended.type
        holder?.itemView.recommend_productBrand?.text = recommended.brand
        holder?.itemView.recommended_productDescription?.text = recommended.description
    }
}

class RecommendedCustomViewHolder(view : View):RecyclerView.ViewHolder(view){}