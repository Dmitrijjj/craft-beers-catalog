package com.dimidroid.beerscatalog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.models.BeerResponseItem

class BeersSearchAdapter(private var beerList: List<BeerResponseItem>) :
RecyclerView.Adapter<BeersSearchAdapter.BeersViewHolder>(), Filterable {

    var filteredBeersCatalog: ArrayList<BeerResponseItem> = ArrayList()

    inner class BeersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var beerImage: ImageView = itemView.findViewById(R.id.imageBeer)
        var beerName: TextView = itemView.findViewById(R.id.nameBeer)
        var beerAbv: TextView = itemView.findViewById(R.id.abvBeer)
    }

    init {
        beerList = filteredBeersCatalog
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        return BeersViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.beer_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        val beerItem = beerList[position]
        holder.apply {
            Glide.with(itemView.context).load(beerItem.imageUrl).into(beerImage)
            beerName.text = beerItem.name
            beerAbv.text = beerItem.abv.toString()

        }
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredBeersCatalog = beerList as ArrayList<BeerResponseItem>
                } else {
                    val resultList = ArrayList<BeerResponseItem>()
                    for (row in beerList) {
                        if (row.name.lowercase().contains(constraint.toString().lowercase())) {
                            resultList.add(row)
                        }
                    }
                    filteredBeersCatalog = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredBeersCatalog
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredBeersCatalog = results?.values as ArrayList<BeerResponseItem>
                notifyDataSetChanged()
            }
        }
    }

}