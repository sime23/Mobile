package com.example.mpmyapp

/**
 * Data class representing a luxury property.
 * 
 * @property id Unique identifier for the property.
 * @property name The name of the property.
 * @property price The rental or purchase price as a string.
 * @property location The geographical location of the property.
 * @property imageRes Resource ID for the property's image.
 * @property description A detailed description of the property features.
 */
data class Property(
    val id: Int,
    val name: String,
    val price: String,
    val location: String,
    val imageRes: Int,
    val description: String
)
