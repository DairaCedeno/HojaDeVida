package com.example.medicoapp.ui.cita
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicoapp.data.Cita
import com.example.medicoapp.data.CitaRepository
import com.example.medicoapp.ui.medico.MedicoViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
class CitaViewModel(
    savedStateHandle: SavedStateHandle,
    citaRepository: CitaRepository
) : ViewModel() {
    private val citaId: Int = checkNotNull(savedStateHandle[CitaDestination.citaIdArg])

    val citaUiState: StateFlow<CitaDetailsUiState> =
        citaRepository.getAllCitaByMedicoStream(citaId).map { CitaDetailsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(MedicoViewModel.TIMEOUT_MILLIS),
                initialValue = CitaDetailsUiState()
            )
}
data class CitaDetailsUiState(val citaList: List<Cita> = listOf())
