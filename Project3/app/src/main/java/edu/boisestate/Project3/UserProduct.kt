package edu.boisestate.Project3

class UserProduct {

    var id: Int = 0;
    var productName:String? = null
    var productType:String? = null
    var productBrand:String? = null
    var locationOfPurchase:String? = null
    var storeName:String? = null
    var purchaseDate:String? = null
    var expirationDate:String? = null

    constructor(id:Int,
                productName:String,
                productType:String,
                productBrand:String,
                locationOfPurchase:String,
                storeName:String,
                purchaseDate:String,
                expirationDate:String){
        this.id = id
        this.productName = productName
        this.productType = productType
        this.productBrand = productBrand
        this.locationOfPurchase = locationOfPurchase
        this.storeName = storeName
        this.purchaseDate = purchaseDate
        this.expirationDate = expirationDate
    }

    constructor(productName:String,
                productType:String,
                productBrand:String,
                locationOfPurchase:String,
                storeName:String,
                purchaseDate:String,
                expirationDate:String){
        this.productName = productName
        this.productType = productType
        this.productBrand = productBrand
        this.locationOfPurchase = locationOfPurchase
        this.storeName = storeName
        this.purchaseDate = purchaseDate
        this.expirationDate = expirationDate
    }
}