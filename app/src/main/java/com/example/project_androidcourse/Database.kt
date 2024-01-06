package com.example.project_androidcourse

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "SOSs",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE)
    ])
data class SOSDataEntity (
    @PrimaryKey() @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "priority") val priority: Short
) {
    constructor(username: String, priority: Short, text: String) : this(Username, message="help",0)//, 0.toUShort())

}

@Dao interface SOSDataDao{
    @Insert
    fun insertSOS(data: SOSDataEntity)

    @Query("SELECT * FROM SOSs WHERE username = :username")
    fun getmessageByUsername(username: String): String
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