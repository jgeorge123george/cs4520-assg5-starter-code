package com.cs4520.assgn5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "expiryDate") val expiryDate: String?,
)