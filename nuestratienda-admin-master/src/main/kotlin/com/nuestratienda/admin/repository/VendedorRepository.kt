package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Vendedor
import org.springframework.data.repository.CrudRepository

interface VendedorRepository : CrudRepository<Vendedor, Long> {
    fun findByCorreo(email: String): Vendedor?

}