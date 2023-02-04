package com.shandorino.binchecker.view_model

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shandorino.binchecker.data.model.Card
import com.shandorino.binchecker.di.repositories.ApiRepository
import com.shandorino.binchecker.di.repositories.DataBaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val api: ApiRepository,
    private val db: DataBaseRepository,
    private val context: Context
) : ViewModel() {

    val currentCard = MutableStateFlow<Card?>(null)

    val bin = MutableStateFlow("")


    val cardList = MutableStateFlow<List<Card>>(listOf())

    fun getNewCard(bin: Int) {
        viewModelScope.launch {

            api.getCard(bin.toString()).collect {
                currentCard.emit(it)
                db.insertCard(it).collect{str ->
                    println(str)
                    getCardList()
                }
            }
        }

    }

    fun getOldCard(id: Int) {
        viewModelScope.launch {

            db.getCard(id).collect {
                currentCard.emit(it)
            }

        }
    }

    fun getCardList() {
        viewModelScope.launch {
            db.getCards().collect {
                cardList.emit(it)
            }
        }
    }

    fun toCallApp(number: String) {

        val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(callIntent)

    }

    fun toBrowser(url: String) {
        val browserIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(browserIntent)
    }

    fun toMapsApp(lat: Int, long: Int) {

        val mapsIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${lat},$long")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(mapsIntent)

    }

    fun onBinChanged(bin: String) {
        this.bin.value = bin
    }

}