package com.nikhil.cryptocurrency.presentation.ui.coin.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.cryptocurrency.commons.Constants
import com.nikhil.cryptocurrency.data.remote.usecase.details.GetCoinDetailsUseCase
import com.nikhil.cryptocurrency.domain.model.CoinDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val savedStateHandler: SavedStateHandle
) : ViewModel() {
    sealed class UIState {
        object Loading : UIState()
        data class Success(val details: CoinDetails) : UIState()
        data class Error(val error: String) : UIState()
    }

    private val _uiState = mutableStateOf<UIState>(UIState.Loading)
    val uiState: State<UIState> = _uiState

    init {
        savedStateHandler.get<String>(Constants.COIN_ID)?.let {
            getCoin(it)
        }
    }

    fun getCoin(id: String) {

        viewModelScope.launch {
            try {
                _uiState.value = UIState.Success(getCoinDetailsUseCase(id))

            } catch (e: HttpException) {
                _uiState.value = UIState.Error(e.localizedMessage ?: "HttpException")

            } catch (e: IOException) {
                _uiState.value = UIState.Error(e.localizedMessage ?: "IOException")
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.localizedMessage ?: "Unknown Exception")
            }
        }
    }
}


