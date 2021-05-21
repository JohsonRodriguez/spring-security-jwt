package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Suscripcion
import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.repository.VendedorRepository
import com.nuestratienda.admin.service.SuscripcionService
import com.nuestratienda.admin.service.VendedorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.websocket.server.PathParam

@RestController
//@CrossOrigin(origins = arrayOf("*"), methods= arrayOf(RequestMethod.GET, RequestMethod.POST))
@RequestMapping("/api/vendedor")
class VendedorController (
    val service: VendedorService,
    val suscripcionService: SuscripcionService) {

    @PostMapping("/registro" , produces = arrayOf("application/json"))
    fun saveNewUser(@RequestBody vendedor: Vendedor): ResponseEntity<Any> {
        val mensaje: MutableMap<String, String> = HashMap()
        mensaje.put("mensaje", service.saveNewUser(vendedor))
        return ResponseEntity<Any>(mensaje, HttpStatus.OK)
    }

    @GetMapping( "/{id}", produces = arrayOf("application/json"))
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any> {
        val vendedor = service.getUserById(id)
        if (vendedor.correo.isBlank()) return ResponseEntity("Registro no encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity(service.getUserById(id), HttpStatus.OK)
    }

    @GetMapping("/suscripcion/{id}", produces = arrayOf("application/json"))
    fun getSuscripcion(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity(suscripcionService.getSuscripcionById(id), HttpStatus.OK)
    }

    @PostMapping("/renovar", produces = arrayOf("application/json"))
    fun renewSuscripcion(@RequestBody suscripcion: Suscripcion): ResponseEntity<Any>{
        val mensaje: MutableMap<String, String> = HashMap()
        mensaje.put("mensaje", service.renewSuscripcion(suscripcion))
        return ResponseEntity<Any>(mensaje, HttpStatus.OK)
    }

    @GetMapping("/listar", produces = arrayOf("application/json"))
    fun getVendedores(): ResponseEntity<Any> {
        return ResponseEntity(service.getVendedores(), HttpStatus.OK)
    }
}