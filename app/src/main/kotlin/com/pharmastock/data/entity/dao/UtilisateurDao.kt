package com.pharmastock.data.dao

import androidx.room.*
import com.pharmastock.data.entity.Utilisateur
import kotlinx.coroutines.flow.Flow

@Dao
interface UtilisateurDao {
    @Insert
    suspend fun insert(utilisateur: Utilisateur): Long

    @Query("SELECT * FROM utilisateurs WHERE login = :login AND hashMotDePasse = :motDePasse")
    suspend fun login(login: String, motDePasse: String): Utilisateur?

    @Query("SELECT * FROM utilisateurs WHERE login = :login")
    suspend fun findByLogin(login: String): Utilisateur?

    @Query("SELECT * FROM utilisateurs")
    fun getAll(): Flow<List<Utilisateur>>
}
