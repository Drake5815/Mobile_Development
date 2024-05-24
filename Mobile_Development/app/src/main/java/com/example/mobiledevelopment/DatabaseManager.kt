package com.example.mobiledevelopment

import com.mongodb.client.MongoClient
import org.bson.Document
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase


class DatabaseManager {

    private val connectionString = "mongodb+srv://jonelgie_Grave:GraveDeath123@grailnruin.eias6eg.mongodb.net/"
    private val client: MongoClient = MongoClients.create(connectionString)
    private val database: MongoDatabase = client.getDatabase("MobileDevelopment")

    private val collection = database.getCollection("Clients")

    fun InsertDocument(){

    }
    fun CreateDocument(){

    }
    fun Update(){

    }
    fun GetDocuments(){

    }
    fun DisplayImages(){

    }
    fun ShowInformation() {

    }
    fun CreateUser(){

    }
    /*
    Create new Slot emphasizes sellable house that comes with images and information of the house
    all emphasized would be come a Json file and convert it into variable information for app
     */
    fun CreateNewSlot(){

    }
}