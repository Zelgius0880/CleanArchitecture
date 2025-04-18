package com.sample.architecture.ui.demo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EchoScreen(
    viewModel: EchoViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(
            label = { Text(text = "Echo") },
            value = uiState.string,
            onValueChange = { viewModel.proceedIntent(EchoAction.TextChanged(it)) }
        )

        AnimatedVisibility(visible = uiState.echo != null) {
            Text(
                text = "Response: ${uiState.echo ?: ""}",
            )
        }

        AnimatedVisibility(visible = uiState.isLoading) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(visible = !uiState.isLoading) {
            Button(
                onClick = { viewModel.proceedIntent(EchoAction.Send) },
            ) {
                Text(text = "Send")
            }
        }

    }
}