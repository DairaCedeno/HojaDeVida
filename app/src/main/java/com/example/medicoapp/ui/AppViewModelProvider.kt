package com.example.medicoapp.ui
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medicoapp.MedicoCitaApplication
import com.example.medicoapp.ui.cita.CitaViewModel
import com.example.medicoapp.ui.medico.MedicoViewModel
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MedicoViewModel(MedicoCitaApplication().container.medicoRepository)
        }
        initializer {
            CitaViewModel(
                this.createSavedStateHandle(),
                MedicoCitaApplication().container.citaRepository
            )
        }
    }
}
fun CreationExtras.MedicoCitaApplication(): MedicoCitaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY]  as MedicoCitaApplication)
