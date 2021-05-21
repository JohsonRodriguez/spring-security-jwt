package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.PusherRequest
import com.pusher.rest.Pusher
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pusher")
class PusherController {

    @PostMapping("/auth", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = ["application/json"])
    fun update(req: PusherRequest): ResponseEntity<Any> {
        val pusher = Pusher("1158630", "7357430a418c50d7acec", "0804eeecd910f7bb9c9d")
        val socketId = req.socket_id
        val channel = req.channel_name
        val auth = pusher.authenticate(socketId, channel)
        System.out.println(auth)
        return ResponseEntity(auth, HttpStatus.OK)
    }
}
