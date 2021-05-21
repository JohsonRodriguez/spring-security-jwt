package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Suscripcion
import org.springframework.data.repository.CrudRepository

interface SuscripcionRepository : CrudRepository<Suscripcion, Long> {
}