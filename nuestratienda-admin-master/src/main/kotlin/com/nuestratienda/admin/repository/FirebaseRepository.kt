package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Firebase
import org.springframework.data.repository.CrudRepository

interface FirebaseRepository : CrudRepository<Firebase, Long> {
}