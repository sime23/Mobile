package com.example.mpmyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the RecyclerView in HomeActivity to display a list of luxury properties.
 * Handles the creation of view holders and binding of property data to the UI.
 */
class PropertyAdapter(private val propertyList: List<Property>) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    /**
     * ViewHolder class that holds references to the UI components for each property item.
     */
    class PropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val propertyImage: ImageView = view.findViewById(R.id.propertyImage)
        val propertyName: TextView = view.findViewById(R.id.propertyName)
        val propertyPrice: TextView = view.findViewById(R.id.propertyPrice)
        val propertyDescription: TextView = view.findViewById(R.id.propertyDescription)
        val bookButton: Button = view.findViewById(R.id.bookButton)
    }

    /**
     * Inflates the item layout (item_property.xml) and creates a new ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    /**
     * Binds data from the property list to the views in the ViewHolder.
     * Also sets up click listeners and entrance animations.
     */
    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        
        // Set property details
        holder.propertyName.text = property.name
        holder.propertyPrice.text = property.price
        holder.propertyDescription.text = property.description
        holder.propertyImage.setImageResource(property.imageRes)

        // Handle 'Book' button clicks
        holder.bookButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Booking ${property.name}...", Toast.LENGTH_SHORT).show()
        }

        // Apply a slide-up animation for a smooth, luxury feel as items appear
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_up)
        holder.itemView.startAnimation(animation)
    }

    /**
     * Returns the total number of items in the property list.
     */
    override fun getItemCount() = propertyList.size
}
