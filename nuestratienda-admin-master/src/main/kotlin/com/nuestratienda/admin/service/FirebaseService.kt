package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Firebase
import com.nuestratienda.admin.repository.FirebaseRepository
import org.springframework.stereotype.Service

@Service
class FirebaseService (
    var repository: FirebaseRepository) {

    fun saveFirebase(firebase: Firebase): Firebase? = repository.save(firebase)

    fun getFirebaseById(idFirebase: Long) : Firebase? = repository.findById(idFirebase).get()
}