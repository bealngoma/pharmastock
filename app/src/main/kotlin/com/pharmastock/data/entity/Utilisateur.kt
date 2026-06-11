package com.pharmastock.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "utilisateurs")
data class Utilisateur(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nom: String,
    val login: String,
    val hashMotDePasse: String,
    val role: String, // "magasinier", "admin"
    val zone: String,
    val dateCreation: Date = Date()
)
