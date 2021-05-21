package com.nuestratienda.admin.model

import javax.persistence.*

@Entity
data class Tienda (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTienda: Long = 0,

    @Column
    var nombre: String = "",

    @Column
    var detalle: String = "",

    @Column
    var logoUrl:String = "",

    @Column
    var direccion: String = "",

    @Column
    var color1: String = "",

    @Column
    var color2: String = ""
        ) {
}