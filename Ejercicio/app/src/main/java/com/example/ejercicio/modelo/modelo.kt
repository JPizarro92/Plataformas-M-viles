package com.example.ejercicio.modelo

import java.util.*

open class Persona (var cedula: String?, var nombres: String?, var telefono:String? )

class Guia(cedula: String?,
           nombres: String?,
           telefono:String?,
           var descripcion:String?,
           var status:Boolean?):Persona(cedula, nombres, telefono)

data class Vuelo( var codigo:Int?, var destino:String?, var fechaSalida: Date?, var fechaRegreso: Date?,
                  var costo: Double?, var cliente:Persona?, var guia:Guia?)