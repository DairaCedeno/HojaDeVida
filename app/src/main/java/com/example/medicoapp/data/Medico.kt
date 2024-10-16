package com.example.medicoapp.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "medico")
data class Medico(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val especialidad: String,
    val uriImagen: String
)
