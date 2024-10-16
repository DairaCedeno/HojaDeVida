package com.example.medicoapp.data
import kotlinx.coroutines.flow.Flow
class MedicoOfflineRepository(private val medicoDao: MedicoDao) : MedicoRepository {
    override fun insertMedico(medico: Medico) = medicoDao.insertMedico(medico)
    override suspend fun getAllMedico(): Flow<List<Medico>> = medicoDao.getAllMedico()
    override fun getAllMedicoStream(): Flow<List<Medico>> = medicoDao.getAllMedico()
    override fun getMedicoStream(id: Int): Flow<Medico?> = medicoDao.getMedico(id)
    }
