package com.example.medicoapp.ui.medico
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicoapp.data.Medico
import com.example.medicoapp.data.MedicoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
class MedicoViewModel(private val medicoRepository: MedicoRepository) : ViewModel() {

    val medicoListUiState: StateFlow<MedicoListUiState> =
        medicoRepository.getAllMedicoStream().map { MedicoListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MedicoListUiState()
            )
    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}
data class MedicoListUiState(val medicoList: List<Medico> = listOf())
