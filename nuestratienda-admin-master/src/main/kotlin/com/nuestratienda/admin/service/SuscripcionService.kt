package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Suscripcion
import com.nuestratienda.admin.repository.SuscripcionRepository
import org.springframework.stereotype.Service

@Service
class SuscripcionService (
    val repository: SuscripcionRepository
        ) {

    fun getSuscripcionById(id: Long): Suscripcion = repository.findById(id).get()

}