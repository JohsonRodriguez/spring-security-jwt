package employeebyron.business

import employeebyron.model.Persona

interface IPersonaBusiness {
    fun list(): List<Persona>
    fun load(idPersona: Long):Persona
    fun save(persona: Persona):Persona
    fun remove(idPersona: Long)

}