@file:OptIn(InternalSerializationApi::class)
package com.example.praktikumlocalrestapi.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikumlocalrestapi.modeldata.DataSiswa
import com.example.praktikumlocalrestapi.repositori.RepositoryDataSiswa
import com.example.praktikumlocalrestapi.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import retrofit2.HttpException
import java.io.IOException


sealed interface StatusUiDetail {
    data class Success(val satuSiswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var statusUiDetail: StatusUiDetail by mutableStateOf(StatusUiDetail.Loading)
        private set
    init {
        getSatuSiswa()
    }
    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUiDetail = StatusUiDetail.Loading
            statusUiDetail = try {
                StatusUiDetail.Success(
                    satuSiswa = repositoryDataSiswa.getSatuSiswa(idSiswa)
                )
            } catch (e: IOException) {
                StatusUiDetail.Error
            } catch (e: HttpException) {
                StatusUiDetail.Error
            }
        }
    }


    @SuppressLint("SuspiciousIndentation")
    fun hapusSatuSiswa() {
        viewModelScope.launch {
            try {
                repositoryDataSiswa.hapusSatuSiswa(idSiswa)
            } catch (e: Exception) {

            }
        }
    }
}
