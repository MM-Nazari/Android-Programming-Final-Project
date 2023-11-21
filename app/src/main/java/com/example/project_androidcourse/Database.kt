package com.example.project_androidcourse

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "SOSs")
data class SOSDataEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val text: String,
    val priority: Short
) {
    constructor(): this(0,"متن پیشفرض", 0)//, 0.toUShort())

}

@Dao interface SOSDataDao{
    @Insert
    suspend fun insertSOS(data: SOSDataEntity)
}

@Database(entities = [SOSDataEntity::class], version = 1, exportSchema = false)
abstract class SOSDatabase : RoomDatabase() {
    abstract fun getDatabaseDao(): SOSDataDao

    companion object {
        @Volatile
        private var instance: SOSDatabase? = null
        fun getDatabase(context: Context): SOSDatabase {
            return instance ?: synchronized(this) {
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    SOSDatabase::class.java,
                    "SOS_DATABASE"
                ).build()
                instance = _instance
                _instance
            }
        }
    }
}