package com.pharmastock.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "lots",
    foreignKeys = [
        ForeignKey(
            entity = Produit::class,
            parentColumns = ["id"],
            childColumns = ["produitId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Lot(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val numeroLot: String,
    val produitId: Long,
    val dateFabrication: Date,
    val dateExpiration: Date,
    var quantite: Int
)