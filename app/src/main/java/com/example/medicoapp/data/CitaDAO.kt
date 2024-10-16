package com.example.medicoapp.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCita(cita: Cita)
    @Query("SELECT * FROM cita WHERE medicoId = :medicoId")
    fun getAllCitaByMedico(medicoId: Int): Flow<List<Cita>>
}
