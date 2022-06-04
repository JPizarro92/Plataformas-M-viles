package ec.edu.ups.moviles.vista

import androidx.annotation.NonNull
import ec.edu.ups.moviles.controlador.CopiasLibro
import ec.edu.ups.moviles.controlador.Lectores
import ec.edu.ups.moviles.controlador.Prestamos


import ec.edu.ups.moviles.modelo.Autor
import ec.edu.ups.moviles.modelo.CopiaLibro
import ec.edu.ups.moviles.modelo.Lector
import ec.edu.ups.moviles.modelo.Prestamo
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

val formato = SimpleDateFormat("dd/MM/yyyy")
var ctlLectores = Lectores()
var ctlCopias = CopiasLibro()
var ctlPrestamos = Prestamos()

fun main(){
    if(ctlLectores.lectores.isEmpty()){
        ctlLectores.cargarLectores()
        //ctlLectores.lectores.forEach { l -> println(l.nombre+" "+l.cedula) }
    }
    if(ctlCopias.copiasLibro.isEmpty()){
        ctlCopias.cargarCopias()
    }

    if(ctlPrestamos.prestamos.isEmpty()){
        ctlPrestamos.cargarPrestamos(ctlCopias, ctlLectores)
    }

    while (3==3){
        print(menu())
        var opc:Int
        opc = readLine()!!.toInt();
        when(opc){
            1->{
                while(3==3){
                    println(menuDefault("Lectores"))
                    var opcL: Int = readLine()!!.toInt()
                    when(opcL){
                        1->{
                            println("Ingrese Cédula")
                            var c: String = readLine()!!.toString()
                            println("Ingrese Nombre Completo")
                            var n: String = readLine()!!.toString()
                            var lector = Lector(n,c)
                            if (ctlLectores.agregarLector(lector)){
                                println("-------------------------------------")
                                println("\tLector agregado exitosamente\n"+
                                        "Cédula: $c \n" +
                                        "Nombre: $n ")
                                println("-------------------------------------")
                            }
                        }
                        2->{
                            println("Lectores: ")
                            ctlLectores.lectores.forEach({c -> println("\tCédula: " + c.cedula +
                                    "\n\tNombres: "+ c.nombre +
                                    "\n -----------------------------------")})
                        }
                        0-> break
                    }
                }
            }
            2->{
                while (3==3){
                    println(menuDefault("Copias"))
                    var opcL: Int = readLine()!!.toInt()
                    when(opcL){
                        1->{
                            println("Información del libro---------")
                            println("Ingrese Titulo del libro:")
                            var nombre: String = readLine()!!.toString()
                            println("Ingrese que tipo es (novela, teatro, poesía, ensayo):")
                            var tipo: String = readLine()!!.toString()
                            println("Ingrese la editorial:")
                            var editorial: String = readLine()!!.toString()
                            println("Ingrese el año de publicación:")
                            var year: Int = readLine()!!.toInt()
                            println("Información del autor---------")
                            println("Ingrese el nombre del autor:")
                            var autorNombre: String = readLine()!!.toString()
                            println("Ingrese la nacionalidad del autor:")
                            var autorNacionalidad: String = readLine()!!.toString()
                            println("Ingrese fecha nacimiento del autor:")
                            var autorFechaNacimiento: Date = formato.parse(readLine()!!.toString())
                            var estado: String = "Biblioteca"

                            var autor = Autor(autorNombre,autorNacionalidad,autorFechaNacimiento)

                            var copia = CopiaLibro(ctlCopias.copiasLibro.size+1, nombre,tipo,editorial,year,autor,estado)

                            if (ctlCopias.agregarCopia(copia)){
                                println("-------------------------------------")
                                println("\tLibro agregado exitosamente\n"+
                                        "Nombre: $nombre \n" +
                                        "Autor: $autorNombre \n" +
                                        "Tipo: $tipo")
                                println("-------------------------------------")
                            }
                        }
                        2->{
                            println("Libros: ")
                            ctlCopias.copiasLibro.forEach({
                                    c -> println("\tTitulo: " + c.nombre +
                                    "\n\tTipo: "+ c.tipo +
                                    "\n\tEditorial: "+ c.editorial+
                                    "\n\tAño publicación: "+ c.year+
                                    "\n\tAutor: "+ c.autor.nombre + "(${c.autor.fechaNacimiento}/${c.autor.nacionalidad})"+
                                    "\n\tEstado: ${c.estado}"+
                                    "\n -----------------------------------")})
                        }
                        0-> {
                            break
                        }
                    }
                }
            }
            3->{
                while (3==3){
                    println(menuPrestamos("Prestamo"))
                    var opcAux: Int = readLine()!!.toInt()
                    when(opcAux){
                        1->{
                            println("Ingrese cédula del Lector")
                            var cedula:String = readLine().toString()
                            var lector: Lector = ctlLectores.lectores.first() { l -> l.cedula==cedula }
                            //println(lector.nombre)
                            println("Ingrese Cod Libro")
                            var codigoLibro:Int = readLine()!!.toInt()
                            var copia: CopiaLibro = ctlCopias.copiasLibro.first(){ l -> l.codigo==codigoLibro}

                            if(ctlPrestamos.prestamos.filter { p -> p.lector.cedula==cedula}
                                                     .filter { p -> p.copiaLibro.estado.uppercase()!="BIBLIOTECA" }
                                                     .count()>=3){
                                println("El usuario ${lector.nombre} cuenta ya con mas de 3 copias prestadas.")
                            }else if(copia.estado.uppercase()!="BIBLIOTECA"){
                                println("El libro \"${copia.nombre}\" no se encuentra disponible para prestamo.")
                            }else{
                                println("Ingrese fecha salida: ")
                                var fecha: Date = formato.parse(readLine().toString())
                                copia.estado="Prestado"
                                var prestamo = Prestamo(ctlPrestamos.prestamos.size+1,copia,fecha,null,lector)
                                if (ctlPrestamos.agregarPrestamo(prestamo)){
                                    //ctlPrestamos.guardarPrestamos()
                                }
                            }
                        }
                        2->{
                            println("Ingrese Cédula del lector")
                            var cedula:String = readLine().toString()
                            var lector = ctlLectores.lectores.first(){p-> p.cedula == cedula}
                            var prestamos = ctlPrestamos.prestamos.filter { p -> p.lector.cedula==cedula }.filter { p -> p.fechaRegreso==null }
                            println("Prestamos activos de ${lector.nombre}")
                            prestamos.forEach{
                                p-> println(
                                        "\t Código Prestamo: ${p.codigo}\n"+
                                        "\t Fecha salida: ${p.fechaSalida} \n" +
                                        "\t Fecha ingreso: ${p.fechaRegreso} \n" +
                                        "\t Días en uso: ${p.diasPasados()} \n" +
                                        "\t Multa: ${p.multa()} (días) \n" +
                                        "\t --------------Libro--------------\n" +
                                        "\t\t Libro: ${p.copiaLibro.nombre} \n" +
                                        "\t\t Tipo: ${p.copiaLibro.tipo} \n" +
                                        "\t\t Año lanzamiento: ${p.copiaLibro.year}\n" +
                                        "\t\t Autor: ${p.copiaLibro.autor.nombre} (-${p.copiaLibro.autor.nacionalidad})\n" +
                                        "**********************************************************"
                                )
                            }
                            println("Ingrese el código del prestamo a dar de alta")
                            var codigoP: Int = readLine()!!.toInt()
                            println("Ingrese fecha de ingreso del libro")
                            var fecha: Date = formato.parse(readLine().toString())
                            var prestamo = ctlPrestamos.prestamos.first { p -> p.codigo==codigoP}
                            prestamo.fechaRegreso = fecha
                            prestamo.copiaLibro.estado="Biblioteca"
                        }
                        3->{
                            println("Prestamos----------------------")
                            ctlPrestamos.prestamos.forEach { p -> println(
                                        "\t Fecha salida: ${p.fechaSalida} \n" +
                                        "\t Fecha ingreso: ${p.fechaRegreso} \n" +
                                        "\t Días en uso: ${p.diasPasados()} \n" +
                                        "\t Multa: ${p.multa()} \n" +
                                                "\t --------------Libro--------------\n" +
                                                "\t\t Libro: ${p.copiaLibro.nombre} \n" +
                                                "\t\t Tipo: ${p.copiaLibro.tipo} \n" +
                                                "\t\t Año lanzamiento: ${p.copiaLibro.year}\n" +
                                                "\t\t Autor: ${p.copiaLibro.autor.nombre} (-${p.copiaLibro.autor.nacionalidad})\n" +
                                                "\t --------------Lector--------------\n" +
                                                "\t\t Lector: ${p.lector.nombre} (${p.lector.cedula})\n"+
                                        "**********************************************************"
                            ) }
                        }
                        0->{
                            break
                        }
                    }
                }
            }
            0->{
                ctlLectores.guardarLectores()
                ctlCopias.guardarCopias()
                ctlPrestamos.guardarPrestamos()
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
            "\t [1] Menu Lectores \n" +
            "\t [2] Menu Copias \n" +
            "\t [3] Menu Prestamos \n" +
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