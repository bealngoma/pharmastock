package com.pharmastock.data.dao

import androidx.room.*
import com.pharmastock.data.entity.Produit
import kotlinx.coroutines.flow.Flow

@Dao
interface ProduitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(produit: Produit): Long

    @Update
    suspend fun update(produit: Produit)

    @Delete
    suspend fun delete(produit: Produit)

    @Query("SELECT * FROM produits ORDER BY nom ASC")
    fun getAll(): Flow<List<Produit>>

    @Query("SELECT * FROM produits WHERE id = :id")
    suspend fun getById(id: Long): Produit?

    @Query("SELECT * FROM produits WHERE codeBarre = :codeBarre")
    suspend fun getByCodeBarre(codeBarre: String): Produit?
}
