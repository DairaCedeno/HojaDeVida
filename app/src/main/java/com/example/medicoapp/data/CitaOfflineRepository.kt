package com.example.medicoapp.data
import kotlinx.coroutines.flow.Flow
class CitaOfflineRepository (private val citaDao: CitaDao) : CitaRepository {
    override suspend fun getAllCitaByMedico(medicoId: Int): Flow<List<Cita>> = citaDao.getAllCitaByMedico(medicoId)
    override fun getAllCitaByMedicoStream(medicoId: Int): Flow<List<Cita>> = citaDao.getAllCitaByMedico(medicoId)

}