package com.pharmastock.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "taches_urgentes")
data class TacheUrgente(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titre: String,
    val description: String,
    val priorite: Int, // 1 = haute, 2 = moyenne, 3 = basse
    val dateCreation: Date = Date(),
    var estResolue: Boolean = false
)
