package com.pharmastock.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produits")
data class Produit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nom: String,
    val dosage: String,
    val codeBarre: String? = null,
    var stockTotal: Int = 0,
    val zone: String
)
