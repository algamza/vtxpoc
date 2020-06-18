package com.humaxdigital.automotive.v2xpoc.domain.usecases

import android.util.Log
import com.humaxdigital.automotive.v2xpoc.domain.entities.GEAR
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.flow.Flow

class GetCarUseCase(private val repositories: CarRepository) {
    fun getGearState() : Flow<GEAR> {
        return repositories.getGearState()
    }
}