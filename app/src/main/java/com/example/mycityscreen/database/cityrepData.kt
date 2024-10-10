package com.example.mycityscreen.database

data class City(
    val id: Int,
    val name: String,
    val img: Int,
    val address: Int,
    val pop: String,
    val des: Int,
    val rating :String,
    val locationIds: List<Int> // References location IDs
)

data class Location(
    val id: Int,
    val limg: Int,
    val lname: String,
    val banner: Int,
    val ldescription: String,
    val category: Int,
    val desc: Int,

)