package com.nikhil.cryptocurrency.presentation.ui.coin.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.nikhil.cryptocurrency.presentation.ui.coin.details.CoinDetailsViewModel.UIState
import com.nikhil.cryptocurrency.presentation.ui.coin.details.componsnts.CoinTag
import com.nikhil.cryptocurrency.presentation.ui.coin.details.componsnts.TeamListItem


@Composable
fun CoinDetailsScreen(
    navController: NavController,
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state: UIState = viewModel.uiState.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is UIState.Success -> {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)

                ) {
                    state.details.let { coin ->
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                    style = MaterialTheme.typography.h2,
                                    modifier = Modifier.weight(8f)
                                )
                                Text(
                                    text = if (coin.isActive) "active" else "inactive",
                                    color = if (coin.isActive) Color.Green else Color.Red,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.body2,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .weight(2f)

                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = coin.description,
                                style = MaterialTheme.typography.body2
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.h3
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            FlowRow(
                                mainAxisSpacing = 12.dp,
                                crossAxisSpacing = 12.dp,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                coin.tags.forEach { tag ->
                                    CoinTag(tag = tag)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Team members",
                                style = MaterialTheme.typography.h3
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        items(coin.team){teamMember->
                            TeamListItem(team = teamMember,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                            Divider()

                        }

                    }

                }
            }
            is UIState.Error -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            is UIState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }

    }
}