package com.example.project_androidcourse

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.OnConflictStrategy


@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "password") val password: String
)


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username= :username")
    fun getUserByUsername(username: String) : User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
}





@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getDatabaseDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "UserDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

