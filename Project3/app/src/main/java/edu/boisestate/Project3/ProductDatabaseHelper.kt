package edu.boisestate.Project3

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

        val query:String = ("CREATE TABLE product($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_PRODUCTNAME TEXT," +
                "$COLUMN_PRODUCTTYPE TEXT," +
                "$COLUMN_PRODUCTBRAND TEXT," +
                "$COLUMN_LOCATION TEXT," +
                "$COLUMN_STORENAME TEXT," +
                "$COLUMN_PURCHASEDATE TEXT," +
                "$COLUMN_EXPIRATIONDATE TEXT)")
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

    fun addProduct(userProduct:UserProduct){
        val db:SQLiteDatabase = this.writableDatabase
        val values:ContentValues = ContentValues()
        values.put(COLUMN_PRODUCTNAME, userProduct.productName)
        values.put(COLUMN_PRODUCTTYPE, userProduct.productType)
        values.put(COLUMN_PRODUCTBRAND, userProduct.productBrand)
        values.put(COLUMN_LOCATION, userProduct.locationOfPurchase)
        values.put(COLUMN_STORENAME, userProduct.storeName)
        values.put(COLUMN_PURCHASEDATE, userProduct.purchaseDate)
        values.put(COLUMN_EXPIRATIONDATE, userProduct.expirationDate)
        db.insert("product", null, values)
        db.close()
    }

    /**
     * Deletes list of products from first index.
     */
    fun deleteProduct(productName: String):Boolean{
        var result = false
        val query =
            "SELECT * FROM product WHERE $COLUMN_PRODUCTNAME = \"$productName\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            val id = Integer.parseInt(cursor.getString(0))
            db.delete("product", "$COLUMN_ID = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    /**
     * Get the products.
     */
    //this list will go into user list adapter???
    fun getProducts():List<String>{
        val productList = ArrayList<String>()
        val db:SQLiteDatabase = this.writableDatabase
        val query:String = "SELECT * FROM product"
        val cursor:Cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do {
                //productList.add(cursor.getString(1))
                productList.add(cursor.getString(1))
                productList.add(cursor.getString(2))
                productList.add(cursor.getString(3))
                productList.add(cursor.getString(4))
                productList.add(cursor.getString(5))
                productList.add(cursor.getString(6))
                productList.add(cursor.getString(7))
            }while(cursor.moveToNext())
        }
        db.close()
        return productList
    }

    //defines the variables for the database
    companion object{
        internal val dbname = "productdb"
        internal val factory = null
        internal val version = 4

        val COLUMN_ID = "id"
        val COLUMN_PRODUCTNAME = "product_name"
        val COLUMN_PRODUCTTYPE = "product_type"
        val COLUMN_PRODUCTBRAND = "product_brand"
        val COLUMN_LOCATION = "location_of_purchase"
        val COLUMN_STORENAME = "store_name"
        val COLUMN_PURCHASEDATE = "purchase_date"
        val COLUMN_EXPIRATIONDATE = "expiration_date"
    }

}