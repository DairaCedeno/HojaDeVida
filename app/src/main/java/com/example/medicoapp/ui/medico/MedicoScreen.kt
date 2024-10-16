package com.example.medicoapp.ui.medico

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicoapp.R
import com.example.medicoapp.MedicoCitaTopAppBar
import com.example.medicoapp.data.Medico
import com.example.medicoapp.ui.AppViewModelProvider
import com.example.medicoapp.ui.Navigation

fun switchImage(idMedico: Int): Int {
    return when (idMedico) {
        1 -> R.drawable.medico1
        2 -> R.drawable.medico2
        else -> R.drawable.medico_defaul
    }
}

@Composable
private fun MedicoItem(
    medico: Medico, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ListItem(
                    leadingContent = {
                        Image(
                            painter = painterResource(id = switchImage(medico.id)),
                            contentDescription = medico.nombre,
                            modifier = Modifier
                                .size(50.dp)
                                .border(BorderStroke(1.dp, Color.Black))
                                .background(Color.Transparent)
                        )
                    },
                    overlineContent = { Text(medico.especialidad) },
                    headlineContent = { Text(medico.nombre) }
                )
            }
        }
    }
}

@Composable
private fun MedicoList(
    medicoList: List<Medico>,
    onMedicoClick: (Medico) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = medicoList, key = { it.id }) { medico ->
            MedicoItem(
                medico = medico,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onMedicoClick(medico) }
            )
        }
    }
}

@Composable
private fun MedicoBody(
    medicoList: List<Medico>,
    onMedicoClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (medicoList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_medico),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            MedicoList(
                medicoList = medicoList,
                onMedicoClick = { onMedicoClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MedicoScreen(
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MedicoViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val userUiState by viewModel.medicoListUiState.collectAsState()

    Scaffold(
        topBar = {
            MedicoCitaTopAppBar(
                title = stringResource(MedicoDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        MedicoBody(
            medicoList = userUiState.medicoList,
            onMedicoClick = navigateToDetails,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

object MedicoDestination : Navigation {
    override val route = "medico_list"
    override val titleRes = R.string.title_medico_list
}
