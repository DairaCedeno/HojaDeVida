package com.example.medicoapp.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface MedicoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMedico(medico: Medico)
    @Query("SELECT * FROM medico")
    fun getAllMedico(): Flow<List<Medico>>
    @Query("SELECT * FROM medico WHERE id = :id")
    fun getMedico(id: Int): Flow<Medico>
}

