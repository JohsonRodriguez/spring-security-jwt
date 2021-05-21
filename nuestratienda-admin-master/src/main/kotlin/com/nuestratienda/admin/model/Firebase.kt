package com.nuestratienda.admin.model

import javax.persistence.*

@Entity
class Firebase (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFirebase: Long = 0 ,

    @Column
    var apiKey: String = "",

    @Column
    var authDomain: String = "",

    @Column
    var databaseURL: String = "",

    @Column
    var projectId: String = "",

    @Column
    var storageBucket: String = "",

    @Column
    var messagingSenderId: String = "",

    @Column
    var appId: String = "",

    @Column
    var measurementId: String = "" ) { }