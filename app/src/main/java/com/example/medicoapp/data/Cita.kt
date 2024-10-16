package com.example.medicoapp.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cita")
data class Cita(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val medicoId: Int,
    val fecha: String,
    val descripcion: String
)
