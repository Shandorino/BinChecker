package com.shandorino.binchecker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shandorino.binchecker.view_model.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(vm: MainViewModel = koinViewModel()) {

    val bin by vm.bin.collectAsState()
    val currentCard by vm.currentCard.collectAsState()
    val cardList by vm.cardList.collectAsState()

    LaunchedEffect(key1 = true){
        vm.getCardList()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .weight(0.5f)
        ){
            TextField(value = bin, onValueChange = vm::onBinChanged)
            IconButton(
                onClick = { vm.getNewCard(bin.toInt()) }
            ) {
                Icon(Icons.Default.Search, "")
            }
        }

        Row(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .background(Color(0xFFA0A0A0)),
                horizontalArrangement = Arrangement.Start
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f).fillMaxSize().padding(top = 5.dp, start = 5.dp)
            ) {
                Text(text = "SCHEME / NETWORK")
                currentCard?.scheme?.let { Text(text = it)}
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "BRAND")
                currentCard?.brand?.let { Text(text = it, softWrap = true)}
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "CARD NUMBER")
                Row(
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Column() {
                        Text(text = "LENGTH")
                        currentCard?.number?.let { Text(text = it.length.toString())}
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Column(

                    ) {
                        Text(text = "LUHN")
                        currentCard?.number?.let {
                            if (it.luhn == true) {
                                Row() {
                                    Text(text = "Yes", fontWeight = FontWeight.Bold)
                                    Text(text = "/No")
                                }
                            } else {
                                Row() {
                                    Text(text = "Yes/")
                                    Text(text = "No", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "TYPE")
                currentCard?.type?.let {
                    if (it == "debit") {
                        Row() {
                            Text(text = "Debit", fontWeight = FontWeight.Bold)
                            Text(text = "/Credit")
                        }
                    } else {
                        Row() {
                            Text(text = "Debit/")
                            Text(text = "Credit", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f).fillMaxSize().padding(top = 5.dp, start = 5.dp)
            ) {
                Text(text = "PREPAID")
                currentCard?.prepaid?.let {
                    if (it) {
                        Row() {
                            Text(text = "Yes", fontWeight = FontWeight.Bold)
                            Text(text = "/No")
                        }
                    } else {
                        Row() {
                            Text(text = "Yes/")
                            Text(text = "No", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Column {
                    Text(text = "COUNTRY")
                    currentCard?.country?.let {
                    Column(modifier = Modifier.clickable { it.latitude?.let { latitude ->
                        it.longitude?.let { longitude ->
                            vm.toMapsApp(
                                latitude, longitude
                            )
                        }
                    } }) {
                        Text(text = "${it.emoji} ${it.name}")
                        Text(text = "(latitude: ${it.latitude}, longitude: ${it.longitude})")
                    }

                    }
                }

                Column() {
                    Text(text = "BANK")
                    currentCard?.bank?.let { 
                        Text(text = "${if (it.name != null && it.name != "null") it.name else "?"}, ${if (it.city != null && it.city != "null") it.city else "?"}")
                        Text(text = if (it.phone != null && it.phone != "null") it.phone else "?", modifier = Modifier.clickable { it.phone?.let { it1 ->
                            vm.toCallApp(it1)
                        } })
                        Text(text = if (it.url != null && it.url != "null") it.url else "?", modifier = Modifier.clickable { it.url?.let { url ->
                            vm.toBrowser(url)
                        } })

                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
        ) {
            items(items = cardList.reversed(), itemContent = {
                Card(it, onClick = {
                    it.id?.let { it1 -> vm.getOldCard(it1) }
                })
            })
        }
    }
}

@Preview
@Composable
fun Previewq() {
    MainScreen()
}