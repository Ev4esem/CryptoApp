package com.example.cryptoapp.data.local_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.data.local_source.model.CoinInfoDb

@Database(entities = [CoinInfoDb::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinInfoDao(): CoinInfoDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

}
