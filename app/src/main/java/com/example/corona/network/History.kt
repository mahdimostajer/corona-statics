package com.example.corona.network

data class History(
    val cases: HashMap<String,Int>,
    val deaths: HashMap<String,Int>,
    val recovered: HashMap<String,Int>
)