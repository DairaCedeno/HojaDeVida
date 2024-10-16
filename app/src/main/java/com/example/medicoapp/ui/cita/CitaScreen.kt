package com.example.medicoapp.ui.cita

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicoapp.R
import com.example.medicoapp.data.Cita
import com.example.medicoapp.ui.AppViewModelProvider
import com.example.medicoapp.ui.Navigation
import com.example.medicoapp.MedicoCitaTopAppBar

object CitaDestination : Navigation {
    override val route = "cita_list"
    override val titleRes = R.string.title_cita_list
}

@Composable
private fun CitaItem(
    cita: Cita,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(text = cita.fecha)
            Text(text = cita.descripcion)
        }
    }
}

@Composable
private fun CitaList(
    citaList: List<Cita>,
    modifier: Modifier = Modifier,
    onCitaClick: (Cita) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = citaList, key = { it.id }) { cita ->
            CitaItem(
                cita = cita,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onCitaClick(cita) }
            )
        }
    }
}

@Composable
private fun CitaBody(
    citaList: List<Cita>,
    onCitaClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        if (citaList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_citas),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            )
        } else {
            CitaList(
                citaList = citaList,
                onCitaClick = { onCitaClick(it.id) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CitaScreen(
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CitaViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val userUiState by viewModel.citaListUiState.collectAsState()

    Scaffold(
        topBar = {
            MedicoCitaTopAppBar(
                title = stringResource(CitaDestination.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        CitaBody(
            citaList = userUiState.citaList,
            onCitaClick = navigateToDetails,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}
