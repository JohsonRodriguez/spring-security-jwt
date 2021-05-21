package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Firebase
import com.nuestratienda.admin.service.FirebaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/firebase")
class FirebaseController (
    var service: FirebaseService ){

    @PostMapping("/guardar", produces = arrayOf("application/json"))
    fun saveFirebaseData(@RequestBody firebase: Firebase): ResponseEntity<Any> {
        val dbFirebase = service.saveFirebase(firebase) ?: return ResponseEntity("Registro fallido", HttpStatus.NOT_FOUND)
        return ResponseEntity(firebase, HttpStatus.OK)
    }

    @GetMapping("/{idFirebase}", produces = arrayOf("application/json"))
    fun getFirebaaseData(@PathVariable("idFirebase") idFirebase: Long) : ResponseEntity<Any> {
        val firebase = service.getFirebaseById(idFirebase) ?: return  ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(firebase, HttpStatus.OK)
    }
}