package ec.edu.ups.moviles.vista

import androidx.annotation.NonNull
import ec.edu.ups.moviles.controlador.*
import ec.edu.ups.moviles.modelo.Genero
import ec.edu.ups.moviles.modelo.Temporada

import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

val formato = SimpleDateFormat("dd/MM/yyyy")
var ctlGeneros = Generos()
var ctlActores = Actores()
var ctlPersonajes = Personajes()
var ctlCapitulos = Capitulos()
var ctlTemporadas = Temporadas()
var ctlSeries = Series()

fun main(){
    if(ctlActores.actores.isEmpty()){
        ctlActores.cargarActores()
        //ctlLectores.lectores.forEach { l -> println(l.nombre+" "+l.cedula) }
    }


    while (3==3){
        print(menu())
        var opc:Int
        opc = readLine()!!.toInt();
        when(opc){
            1->{
                println("----------------Nueva Serie----------------")
                var opcL: Int = readLine()!!.toInt()
                println("\tIngrese Titulo de serie:")
                var titulo: String = readLine()!!.toString()
                println("\tIngrese Año de serie:")
                var anio: Int = readLine()!!.toInt()
                println("\tIngrese sinopsis:")
                var sinopsis: String = readLine()!!.toString()
                println("\tSeleccione genero:")
                ctlGeneros.generos.forEach {
                        g -> println("\t["+g.codigo+"] "+g.descripcion)
                }
                println("\t[0] Ingresar descripcion de genero")
                var opcGenero: Int = readLine()!!.toInt()
                var generoDescripcion:String
                if(opcGenero==0){
                    println("\tIngrese el nuevo genero")
                    var genero = Genero( ctlGeneros.generos.size+1, readLine()!!.toString())
                    ctlGeneros.agregarGenero(genero)
                    generoDescripcion = genero.descripcion
                }else {
                    generoDescripcion = ctlGeneros.generos.first { g -> g.codigo==opcGenero }.descripcion
                }
                var temporadas = mutableListOf<Temporada>()
                println("\t-------------------Temporadas")
                while (true){
                    println("\t\tIngrese numero de temporada:")
                    var nTemporada: Int = readLine()!!.toInt()
                    println("\t\tIngrese fecha de producción (dd/MM/yyy)")
                    var fechaP:Date = formato.parse(readLine()!!.toString())
                    println("\t\tIngrese fecha de lanzamiento (dd/MM/yyy)")
                    var fechaL:Date = formato.parse(readLine()!!.toString())
                    println("\t\t-----------------Capitulos:")
                    while (true){
                        println("\t\t\tCapitulo")
                    }



                    }

            }
            2->{



            }
            3->{


            }
            0->{
                //ctlPrestamos.guardarPrestamos()
                print(despedida())
                break
            }
        }
    }
}

fun menu(): String {
    return "****************************************** \n" +
            "\t \t Menu Principal \n" +
            "****************************************** \n" +
            "\t [1] Agregar Series \n" +
            "\t [2] Listar Series \n" +
            "\t [3] Menu Personajes \n" +
            "\t [0] Salir\n"
}

fun menuDefault(description:String): String {
    return "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t \t Menu $description \n" +
            "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t [1] Agregar $description \n" +
            "\t [2] Listar $description \n" +
            "\t [0] Atras\n"
}

fun menuPrestamos(description:String): String {
    return "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t \t Menu $description \n" +
            "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t [1] Ingresar nuevo $description \n" +
            "\t [2] Dar de alta a $description \n" +
            "\t [3] Listar $description \n" +
            "\t [0] Atras\n"
}

fun despedida():String{
    return "******************************************* \n" +
            "\t Gracias por usar nuestro sistema\n" +
            "******************************************* \n" +
            "Realizaro por: Jorge Pizarro \n" +
            "Universidad Politécnica Salesiana"
}