package com.example.medicoapp.data
import android.content.Context
interface AppContainer {
    val medicoRepository: MedicoRepository
    val citaRepository: CitaRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val medicoRepository: MedicoRepository by lazy {
        MedicoOfflineRepository(MedicoDatabase.getDatabase(context).medicoDao())
    }
    override val citaRepository: CitaRepository by lazy {
        CitaOfflineRepository(MedicoDatabase.getDatabase(context).citaDao())
    }
}
