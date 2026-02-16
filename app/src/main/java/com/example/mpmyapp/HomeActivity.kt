package com.example.mpmyapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * HomeActivity serves as the main dashboard where users can browse luxury properties.
 * It displays a personalized welcome message and a list of available properties.
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize RecyclerView and other UI components
        val recyclerView = findViewById<RecyclerView>(R.id.propertyRecyclerView)
        val welcome = findViewById<TextView>(R.id.welcome)

        // Retrieve user information passed from the previous activity via Intent
        val receivedGender = intent.getStringExtra("USER_GENDER")
        val receivedName = intent.getStringExtra("USER_NAME")

        // Personalize the welcome message based on the user's gender and name
        if(receivedGender == "Male"){
            welcome.text = "Welcome Mr ${receivedName} Find your next masterpiece \uD83C\uDFDB\uFE0F"
        }else{
            welcome.text = "Wecome Mrs ${receivedName} Find your next masterpiece \uD83C\uDFDB\uFE0F"
        }

        // Create a static list of luxury property data
        val propertyList = listOf(
            Property(1, "Golden Heights Penthouse", "$5,000/mo", "Manhattan, NY", R.drawable.r1, "Experience ultimate luxury in this sky-high penthouse with breathtaking views of the city skyline."),
            Property(2, "The Onyx Villa", "$12,000/mo", "Beverly Hills, CA", R.drawable.r2, "A modern masterpiece featuring sleek black marble and an infinity pool overlooking the hills."),
            Property(3, "Marble Arch Apartment", "$3,500/mo", "London, UK", R.drawable.r3, "Classic elegance meets modern comfort in this historic district apartment."),
            Property(4, "Royal Sands Estate", "$8,000/mo", "Dubai, UAE", R.drawable.r4, "A sprawling beachfront estate with private access to pristine white sands and turquoise waters.")
        )

        // Set up the RecyclerView with a vertical LinearLayoutManager and the PropertyAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PropertyAdapter(propertyList)
    }
}
