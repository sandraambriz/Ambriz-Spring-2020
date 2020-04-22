package edu.boisestate.p2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * This class creates our user product database table and provides needed functions
 * so that our users can input their personal information.
 */
class ProductDatabaseHelper (context: Context):SQLiteOpenHelper(context, dbname, factory, version){

    /**
     * Provides the CREATE TABLE statement needed for our database.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val query:String = ("CREATE TABLE product(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT)")
        db?.execSQL(query)
    }

    /**
     * Recreates the table.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS product")
        onCreate(db)
    }

    /**
     * Add the product.
     */
    fun addProduct(name:String){
        val db:SQLiteDatabase = this.writableDatabase
        val values:ContentValues = ContentValues()
        values.put("name", name)
        db.insert("product", null, values)
        db.close()
    }

    /**
     * Delete the product.
     */
    fun deleteProduct(name:String):Int{
        val db = this.writableDatabase
        return db.delete("product", "name=?", arrayOf(name))
    }

    /**
     * TODO figure out if this works
     * Updates the product.
     */
    fun updateProduct(name:String):Int{
        val db:SQLiteDatabase = this.writableDatabase
        val values:ContentValues = ContentValues()
        values.put("name", name)
        return db.update("product", values, "name=?", arrayOf(name))
    }

    /**
     * Get the products.
     */
    fun getProducts():List<String>{
        val productList = ArrayList<String>()
        val db:SQLiteDatabase = this.writableDatabase
        val query:String = "SELECT * FROM product"
        val cursor:Cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do {
                productList.add(cursor.getString(1))
            }while(cursor.moveToNext())
        }
        db.close()
        return productList
    }

    //defines the variables for the database
    companion object{
        internal val dbname = "productdb"
        internal val factory = null
        internal val version = 1
    }

}