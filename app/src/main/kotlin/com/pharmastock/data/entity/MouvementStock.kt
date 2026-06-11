package com.pharmastock.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "mouvements_stock",
    foreignKeys = [
        ForeignKey(
            entity = Produit::class,
            parentColumns = ["id"],
            childColumns = ["produitId"]
        ),
        ForeignKey(
            entity = Lot::class,
            parentColumns = ["id"],
            childColumns = ["lotId"]
        ),
        ForeignKey(
            entity = Utilisateur::class,
            parentColumns = ["id"],
            childColumns = ["utilisateurId"]
        )
    ]
)
data class MouvementStock(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String, // "ENTREE" ou "SORTIE"
    val quantite: Int,
    val date: Date = Date(),
    val produitId: Long,
    val lotId: Long? = null,
    val utilisateurId: Long? = null,
    val destination: String? = null // pour les sorties (pharmacie cliente)
)
