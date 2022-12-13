package com.dimidroid.beerscatalog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimidroid.beerscatalog.R
import com.dimidroid.beerscatalog.models.BeerResponseItem

class BeersCatalogAdapter: RecyclerView.Adapter<BeersCatalogAdapter.BeersViewHolder>() {

    var filteredBeersCatalog: List<BeerResponseItem> = ArrayList()

    inner class BeersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var beerImage: ImageView = itemView.findViewById(R.id.imageBeer)
        var beerName: TextView = itemView.findViewById(R.id.nameBeer)
        var beerAbv: TextView = itemView.findViewById(R.id.abvBeer)
        var iconFavourite: ImageView = itemView.findViewById(R.id.iconFav)
    }
    // use diffUtil against notifydatasetchanged
    private val differCallback = object : DiffUtil.ItemCallback<BeerResponseItem>(){
        override fun areItemsTheSame(
            oldItem: BeerResponseItem,
            newItem: BeerResponseItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: BeerResponseItem,
            newItem: BeerResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        return BeersViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.beer_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        val beerItem = differ.currentList[position]
        holder.apply {
            Glide.with(itemView.context).load(beerItem.imageUrl).into(beerImage)
            beerName.text = beerItem.name
            beerAbv.text = beerItem.abv.toString()
            iconFavourite.setOnClickListener {
                //handleSavingBeer(differ.currentList, position, beerItem.imageUrl,
                    //beerItem.name, beerItem.abv.toString())
            }
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}