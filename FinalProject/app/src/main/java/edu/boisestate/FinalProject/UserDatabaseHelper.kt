package edu.boisestate.FinalProject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Class creates the user database table needed to manage new users and pre-existing users.
 */
class UserDatabaseHelper (context:Context):SQLiteOpenHelper(context, dbname, factory,version){

    /**
     * Creates the user table.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "name VARCHAR(255), email VARCHAR(255), password VARCHAR(255))")
    }

    /**
     * Recreates the table.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }

    /**
     * Adds user.
     */
    fun addUser(name:String, email:String, password:String){
        val db:SQLiteDatabase = writableDatabase
        val values:ContentValues = ContentValues()
        values.put("name", name)
        values.put("email", email)
        values.put("password", password)
        db.insert("user", null, values)
        db.close()
    }

    /**
     * Verify user is in database with matching email and password.
     * Used to verify matching login information.
     */
    fun userExists(email:String, password:String):Boolean{
        val db = writableDatabase
        val query = "SELECT * FROM user WHERE email='$email' AND password='$password'"
        val isPresent=db.rawQuery(query, null)
        if(isPresent.count <= 0){
            isPresent.close()
            return false
        }
        isPresent.close()
        return true
    }

    /**
     * Check if user already exists.
     * Used to ensure users who register don't match with existing users.
     */
    fun userAlreadyExists(email:String):Boolean{
        val db = writableDatabase
        val query = "SELECT * FROM user WHERE email='$email'"
        val emailExists=db.rawQuery(query,null)
        if(emailExists.count <= 0){
            emailExists.close()
            return false
        }
        emailExists.close()
        return true
    }

    /**
     * Get contents of user database.
     */
    fun getUsers():List<String>{
        val userList = ArrayList<String>()
        val db= writableDatabase
        val query = "SELECT * FROM user"
        val cursor:Cursor=db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do {
                userList.add(cursor.getString(1))
            }while(cursor.moveToNext())
        }
        db.close()
        return userList
    }


    //defines our database variables.
    companion object{
        internal val dbname = "userdb"
        internal val factory = null
        internal val version = 1
    }
}