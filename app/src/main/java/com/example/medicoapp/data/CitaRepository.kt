package com.example.medicoapp.data
import kotlinx.coroutines.flow.Flow
interface CitaRepository {
    suspend fun getAllCitaByMedico(medicoId: Int): Flow<List<Cita>>
    fun getAllCitaByMedicoStream(medicoId: Int): Flow<List<Cita>>
    }
