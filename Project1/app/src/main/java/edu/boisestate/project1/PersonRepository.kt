package edu.boisestate.project1

import java.util.*

//list of users
object PersonRepository {
    private val personList = mutableListOf<Person>()

    fun personList():List<Person>{
        return Collections.unmodifiableList(personList)
    }

    fun addPerson(person:Person){
        personList.add(person)
    }

    fun removeAll(){
        personList.clear()
    }

    fun loginPerson(profile:Person):Boolean{
        return false
    }
}