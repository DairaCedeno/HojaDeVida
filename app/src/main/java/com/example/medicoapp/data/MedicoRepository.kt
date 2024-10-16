package com.example.medicoapp.data
import kotlinx.coroutines.flow.Flow
interface MedicoRepository {
    fun insertMedico(medico: Medico)
    suspend fun getAllMedico(): Flow<List<Medico>>
    fun getAllMedicoStream(): Flow<List<Medico>>
    fun getMedicoStream(id: Int): Flow<Medico?>
}
