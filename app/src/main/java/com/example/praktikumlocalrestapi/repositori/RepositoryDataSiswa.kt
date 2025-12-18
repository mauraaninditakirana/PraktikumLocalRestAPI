package com.example.praktikumlocalrestapi.repositori

import com.example.praktikumlocalrestapi.apiservice.ServiceApiSiswa
import com.example.praktikumlocalrestapi.modeldata.DataSiswa

interface RepositoryDataSiswa{
    suspend fun getDataSiswa() : List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) :retrofit2.Response<Void>
    //suspend fun getStatusSiswa(id:Int) : DataSiswa
    //suspend fun editStatusSiswa(id:Int,dataSiswa: DataSiswa) :retrofitr.Response<Void)
    //suspend fun hapusStatusSiswa(id:Int) :retrofitr.Response<Void)
}
class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
):RepositoryDataSiswa{
    override suspend fun getDataSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void> = serviceApiSiswa.postSiswa(dataSiswa)

}