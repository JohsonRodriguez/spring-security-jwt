package employeebyron.web

import employeebyron.business.IPersonaBusiness
import employeebyron.exceptions.BusinessException
import employeebyron.exceptions.NotFoundException
import employeebyron.model.Persona
import employeebyron.utils.Constans
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constans.URL_BASE_PERSONAS)
class PersonaRestController {
    @Autowired
    val personaBusiness:IPersonaBusiness?=null
    @GetMapping()
    fun list():ResponseEntity<List<Persona>>{
        return try {
        ResponseEntity(personaBusiness!!.list(),HttpStatus.OK)
        }catch (e:Exception){
        ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id")
    fun load(@PathVariable("id") idPersona:Long):ResponseEntity<Persona>{
        return try{
            ResponseEntity(personaBusiness!!.load(idPersona),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constans.URL_BASE_PERSONAS + "/"+persona.id)
            ResponseEntity(responseHeader,HttpStatus.CREATED)

        }catch (e:BusinessException){
             ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PutMapping("")
    fun update(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any> {
        return try {
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }




}