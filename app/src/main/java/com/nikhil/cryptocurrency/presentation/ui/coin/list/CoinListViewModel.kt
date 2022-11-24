package com.nikhil.cryptocurrency.presentation.ui.coin.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.cryptocurrency.data.remote.usecase.coin.GetCoinUseCase
import com.nikhil.cryptocurrency.domain.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    sealed class UIState {
        object Loading : UIState()
        data class Success(val coinList: List<Coin>) : UIState()
        data class Error(val error: String) : UIState()
    }

    private val _uiState = mutableStateOf<UIState>(UIState.Loading)
    val uiState: State<UIState> = _uiState

    init {
        getCoinList()
    }

    private fun getCoinList() {

        viewModelScope.launch {
            try {
                _uiState.value = UIState.Success(getCoinUseCase())

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








