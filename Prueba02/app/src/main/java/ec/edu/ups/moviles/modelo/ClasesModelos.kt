package ec.edu.ups.moviles.modelo

import java.text.SimpleDateFormat
import java.util.*

open class Persona(nombre: String)
data class Autor(var nombre:String, var nacionalidad:String, var fechaNacimiento:Date): Persona(nombre)
data class Lector(var nombre: String, var cedula:String): Persona(nombre)

open class Libro(codigo:Int, nombre:String , tipo:String, editorial:String, year:Int, autor:Autor)
data class CopiaLibro(var codigo: Int, var nombre:String , var tipo:String,var editorial:String, var year:Int, var autor:Autor, var estado:String):
    Libro(codigo, nombre, tipo, editorial, year, autor)
data class Prestamo(var codigo:Int, var copiaLibro:CopiaLibro, var fechaSalida:Date, var fechaRegreso:Date?, var lector:Lector){
    val formato = SimpleDateFormat("dd/MM/yyyy")
    fun multa(): Int = if (diasPasados()>30) diasPasados()*2 else 0
    fun diasPasados():Int = if(fechaRegreso!=null) ((fechaRegreso!!.time - fechaSalida.time)/(1000*60*60*24)).toInt()
                            else ((Date().time - fechaSalida.time)/(1000*60*60*24)).toInt()
}



