package edu.boisestate.project1

import java.io.Serializable
import java.util.*

// Constructor for our Person
// Immutable val values, mutable var values
// Serializable allows data to be passed between activities
data class Person(
    val firstName:String,
    val lastName:String,
    var age:Int,
    val birthDate:Date = Date()):Serializable {
}