package com.kanna.agendatarea
import com.orm.SugarRecord
class PersonaRepositorio {
    fun crear(nombre:String, correo:String, telefono:String,
                observaciones:String){
        var persona = Persona(nombre, correo, telefono,
                                observaciones)
        SugarRecord.save(persona)
    }
    fun listar():List<Persona>{
        var personas = SugarRecord.listAll(
                                    Persona::class.java)
        return personas
    }
    fun borrar(id:Long){
        var persona: Persona = SugarRecord.findById(
                                Persona::class.java, id)
        SugarRecord.delete(persona)
    }
    fun leer(id:Long):Persona{
        var persona: Persona = SugarRecord.findById(
                                Persona::class.java, id)
        return persona
    }
    fun actualizar(id:Long, nombre:String, correo:String,
                   telefono:String, observaciones:String){
        var persona: Persona = SugarRecord.findById(
                                Persona::class.java, id)
        persona.nombre = nombre
        persona.correo = correo
        persona.telefono = telefono
        persona.observaciones = observaciones
        SugarRecord.save(persona)
    }
}