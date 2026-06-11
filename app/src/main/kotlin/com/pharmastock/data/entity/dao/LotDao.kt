package com.pharmastock.data.dao

import androidx.room.*
import com.pharmastock.data.entity.Lot
import kotlinx.coroutines.flow.Flow

@Dao
interface LotDao {
    @Insert
    suspend fun insert(lot: Lot): Long

    @Update
    suspend fun update(lot: Lot)

    @Query("SELECT * FROM lots WHERE produitId = :produitId ORDER BY dateExpiration ASC")
    fun getByProduitId(produitId: Long): Flow<List<Lot>>

    @Query("SELECT * FROM lots WHERE numeroLot LIKE '%' || :recherche || '%'")
    fun searchByNumero(recherche: String): Flow<List<Lot>>

    @Query("SELECT * FROM lots WHERE dateExpiration < :dateActuelle")
    suspend fun getExpiredLots(dateActuelle: java.util.Date): List<Lot>

    @Query("SELECT * FROM lots WHERE dateExpiration BETWEEN :aujourdhui AND :seuil")
    suspend fun getSoonExpiringLots(aujourdhui: java.util.Date, seuil: java.util.Date): List<Lot>
}
