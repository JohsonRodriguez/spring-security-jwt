package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Tienda
import org.springframework.data.repository.CrudRepository

interface TiendaRepository : CrudRepository<Tienda, Long> {

    fun findByIdTienda (idTienda: Long): Tienda?
}