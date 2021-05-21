package com.nuestratienda.admin.model

import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Suscripcion (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSuscripcion: Long = 0,

    @Column
    var token: String = "",

    @Column
    var fechaFin: Date = Date() ){

}


