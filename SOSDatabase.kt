package com.example.signup

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Database
import androidx.room.ForeignKey
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.util.TableInfo.Index
import androidx.room.OnConflictStrategy


@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "password") val password: String
)

@Entity(tableName = "comments",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE)
    ])
data class Comment(
    @PrimaryKey(autoGenerate = true) val commentId: Int = 0,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "priority") val priority: Int
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username= :username")
    fun findUserByUsername(username: String) : User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
}


@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertComment(comment: Comment)

    @Query("SELECT * FROM comments WHERE username = :username")
    fun getCommentsByUsername(username: String): List<Comment>

    
}


@Database(entities = [User::class, Comment::class], version = 1)
abstract class SOSsDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: SOSsDatabase? = null

        fun getDatabase(context: Context): SOSsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SOSsDatabase::class.java,
                    "SOSs"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

