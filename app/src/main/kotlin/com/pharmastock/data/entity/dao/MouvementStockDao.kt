package com.pharmastock.data.dao

import androidx.room.*
import com.pharmastock.data.entity.MouvementStock
import kotlinx.coroutines.flow.Flow

@Dao
interface MouvementStockDao {
    @Insert
    suspend fun insert(mouvement: MouvementStock): Long

    @Query("SELECT * FROM mouvements_stock ORDER BY date DESC")
    fun getAll(): Flow<List<MouvementStock>>

    @Query("SELECT * FROM mouvements_stock WHERE produitId = :produitId ORDER BY date DESC")
    fun getByProduitId(produitId: Long): Flow<List<MouvementStock>>

    @Query("SELECT * FROM mouvements_stock WHERE lotId = :lotId ORDER BY date DESC")
    fun getByLotId(lotId: Long): Flow<List<MouvementStock>>
}
