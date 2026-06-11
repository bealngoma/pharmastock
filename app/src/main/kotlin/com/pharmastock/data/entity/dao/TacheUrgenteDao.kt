package com.pharmastock.data.dao

import androidx.room.*
import com.pharmastock.data.entity.TacheUrgente
import kotlinx.coroutines.flow.Flow

@Dao
interface TacheUrgenteDao {
    @Insert
    suspend fun insert(tache: TacheUrgente): Long

    @Update
    suspend fun update(tache: TacheUrgente)

    @Query("SELECT * FROM taches_urgentes WHERE estResolue = 0 ORDER BY priorite ASC, dateCreation DESC")
    fun getNonResolues(): Flow<List<TacheUrgente>>
}
