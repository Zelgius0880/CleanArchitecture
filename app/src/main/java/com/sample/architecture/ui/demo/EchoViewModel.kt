package com.sample.architecture.ui.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.architecture.domain.demo.usecase.SendEchoUseCase
import com.sample.architecture.domain.demo.usecase.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EchoViewModel @Inject constructor(
    private val sendEchoUseCase: SendEchoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(EchoUiState())
    val uiState = _uiState.asStateFlow()

    fun proceedIntent(action: EchoAction) {
        when (action) {
            is EchoAction.Send -> sendEcho()
            is EchoAction.TextChanged -> updateString(action.string)
        }
    }

    private fun sendEcho() = viewModelScope.launch {
        _uiState.update {
            it.copy(isLoading = true)
        }

        val result = sendEchoUseCase.execute(_uiState.value.string)

        _uiState.update {
            it.copy(isLoading = false, echo = (result as? Success)?.echo?.echo)
        }
    }

    private fun updateString(string: String) {
        _uiState.update {
            it.copy(string = string)
        }
    }

}

data class EchoUiState(
    val string: String = "",
    val echo: String? = null,
    val isLoading: Boolean = false
)

sealed interface EchoAction {
    data object Send : EchoAction
    data class TextChanged(val string: String) : EchoAction
}