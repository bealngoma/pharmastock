package com.pharmastock.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pharmastock.data.dao.*
import com.pharmastock.data.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Database(
    entities = [
        Utilisateur::class,
        Produit::class,
        Lot::class,
        MouvementStock::class,
        TacheUrgente::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun utilisateurDao(): UtilisateurDao
    abstract fun produitDao(): ProduitDao
    abstract fun lotDao(): LotDao
    abstract fun mouvementStockDao(): MouvementStockDao
    abstract fun tacheUrgenteDao(): TacheUrgenteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pharmastock_db"
                )
                    .addCallback(PrepopulateCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PrepopulateCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        // Ajout d'un utilisateur par défaut
                        val utilisateurDao = database.utilisateurDao()
                        if (utilisateurDao.findByLogin("magasinier") == null) {
                            utilisateurDao.insert(
                                Utilisateur(
                                    nom = "Magasinier Default",
                                    login = "magasinier",
                                    hashMotDePasse = "1234", // ⚠️ à hasher plus tard
                                    role = "magasinier",
                                    zone = "Zone Principale"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
