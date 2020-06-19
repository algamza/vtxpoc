package com.humaxdigital.automotive.v2xpoc.domain.repositories

import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getWarning() : Flow<V2XWarning>
    fun getInform() : Flow<V2XInform>
    fun getVehicle() : Flow<V2XVehicleStatus>
    fun getSPaT() : Flow<V2XSPaT>
}