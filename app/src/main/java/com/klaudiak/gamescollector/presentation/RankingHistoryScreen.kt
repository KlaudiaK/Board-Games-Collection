package com.klaudiak.gamescollector.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klaudiak.gamescollector.viewmodel.RankingHistoryViewModel

@Composable
fun RankingHistoryScreen(
    navController: NavController,
    id: String?,
    rankingHistoryViewModel: RankingHistoryViewModel = hiltViewModel<RankingHistoryViewModel>()
) {
    if (id != null) {
        rankingHistoryViewModel.getGameRankingHistory(id)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Ranking position history", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(6.dp))
            }
        }


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RankingTable(lastPos = rankingHistoryViewModel.state.rankingLastPos)
        }
    }

}



@Composable
fun RankingTable(
    lastPos: String
){
    Text(text = "Actual position in ranking: $lastPos",
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 16.dp, top = 20.dp))
    Divider(modifier = Modifier.padding(
        horizontal = 16.dp,
    ),
    color = MaterialTheme.colors.secondary,
    thickness = 8.dp)


    Column( modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Ranking History",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp))
    }


}