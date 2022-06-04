package ec.edu.ups.moviles.modelo

import java.text.SimpleDateFormat
import java.util.*

/*data class Prestamo(var codigo:Int, var copiaLibro:CopiaLibro, var fechaSalida:Date, var fechaRegreso:Date?, var lector:Lector){
    val formato = SimpleDateFormat("dd/MM/yyyy")
    fun multa(): Int = if (diasPasados()>30) diasPasados()*2 else 0
    fun diasPasados():Int = if(fechaRegreso!=null) ((fechaRegreso!!.time - fechaSalida.time)/(1000*60*60*24)).toInt()
                            else ((Date().time - fechaSalida.time)/(1000*60*60*24)).toInt()
}*/

data class Actor(var codigo: Int, var nombre: String)
data class Personaje(var codigo: Int, var nombre: String, var actor:Actor)
data class Capitulo(var codigo: Int, var tituloCapitulo:String,
                    var duracion:String, var sinopsis:String, var actores: List<Actor>)
data class Temporada(var codigo: Int, var numeroTemporada:Int, var fechaProduccion:Date,
                     var fechaLanzamiento:Date, var capitulos: List<Capitulo>)
data class Serie(var codigo: Int, var tituloSerie:String, var anio:Int, var sinopsisSerie:String,
                 var genero:String,var temporadas:List<Temporada>, var personajes:List<Personaje>)
data class Genero(var codigo: Int, var descripcion:String)