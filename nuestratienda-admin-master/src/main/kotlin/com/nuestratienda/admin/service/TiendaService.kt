package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.repository.TiendaRepository
import org.springframework.stereotype.Service

@Service
class TiendaService (
    var repository: TiendaRepository) {

    fun update(tienda: Tienda) = repository.save(tienda)

    fun getStoreById(idTienda: Long): Tienda? {
        var tienda = Tienda()
        try {
            tienda = repository.findByIdTienda(idTienda)!!
        }catch (e: Exception){ }
        return tienda
    }


}