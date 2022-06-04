package com.example.ejercicio

import com.example.ejercicio.modelo.Guia
import com.example.ejercicio.modelo.Persona
import com.example.ejercicio.modelo.Vuelo
import java.io.*
import java.text.SimpleDateFormat

var clientes = mutableListOf<Persona>()
var guias = mutableListOf<Guia>()
var vuelos = mutableListOf<Vuelo>()
fun main() {
    val formato = SimpleDateFormat("dd/MM/yyyy")
    var bandera:Boolean = true;
    while (bandera){
        print(menu())
        var opc:Int
        opc = readLine()!!.toInt();
        when(opc){
            1 ->
            {
                var banderaC = true
                while (banderaC){
                    println(menuDefault("Cliente"))
                    var opcC: Int = readLine()!!.toInt()
                    when(opcC){
                        1->
                        {
                            println("Ingrese Cédula")
                            var c: String = readLine()!!.toString()
                            println("Ingrese Nombre Completo")
                            var n: String = readLine()!!.toString()
                            println("Ingrese Telefono")
                            var t: String = readLine()!!.toString()
                            var cliente = Persona(c, n, t)
                            clientes.add(cliente)
                        }
                        2-> {
                            println("Clientes: ")
                            clientes.forEach({c -> println("\tCédula: " + c.cedula +
                                                         "\n\tNombres: "+ c.nombres +
                                                        "\n\tTelefono: " + c.telefono)})
                        }
                        3->
                            banderaC = false
                    }
                }
            }
            2 ->
            {
                var banderaG = true
                while (banderaG){
                    println(menuDefault("Guias"))
                    var opcC: Int = readLine()!!.toInt()
                    when(opcC){
                        1->
                        {
                            println("Ingrese Cédula")
                            var c: String = readLine()!!.toString()
                            println("Ingrese Nombre Completo")
                            var n: String = readLine()!!.toString()
                            println("Ingrese Telefono")
                            var t: String = readLine()!!.toString()
                            println("Ingrese la especialidad")
                            var e: String = readLine()!!.toString()
                            var s: Boolean = true
                            var g = Guia(e,s)
                            g.cedula = c
                            g.nombres = n
                            g.telefono = t
                            guias.add(g)
                        }
                        2-> {
                            println("Guias: ")
                            guias.forEach({c -> println("\tCédula: " + c.cedula +
                                    "\n\tNombres: "+ c.nombres +
                                    "\n\tTelefono: " + c.telefono +
                                    "\n\tEspecialidad: " + c.descripcion +
                                    "\n\tEstado: " + if (c.status==true) "Disponible" else "No Disponible"
                            )})
                        }
                        3->
                            banderaG = false
                    }
                }
            }
            3 ->
            {
                var banderaG = true
                while (banderaG){
                    println(menuDefault("Vuelos"))
                    var opcC: Int = readLine()!!.toInt()
                    when(opcC){
                        1->
                        {
                            println("Código de vuelo: " + (vuelos.size+1))
                            println("Agregue destino: ")
                            var d: String = readLine()!!.toString()
                            println("Fecha salida: (dd/MM/yyyy)")
                            var fs:String = readLine()!!.toString()
                            println("Fecha retorno: (dd/MM/yyyy)")
                            var fr:String = readLine()!!.toString()
                            println("Costo $ de vuelo: ")
                            var c:Double = readLine()!!.toDouble()
                            println("Cédula de cliente: ")
                            var cCliente:String = readLine()!!.toString()
                            var clienteObj = clientes.find {cli -> cli.cedula == cCliente  }
                            println("Cédula de guia: ")
                            var cGuia:String = readLine()!!.toString()
                            var guiaObj = guias.find {cli -> cli.cedula == cGuia  }
                            var vuelo = Vuelo(vuelos.size+1,d,formato.parse(fs),formato.parse(fr),c,clienteObj,guiaObj)
                            vuelos.add(vuelo)
                        }
                        2-> {
                            println("Vuelos: ")
                            vuelos.forEach({v -> println(
                                        "************************************\n" +
                                                "Destino:" + v.destino +
                                                "\nFecha salida: " + v.fechaSalida +
                                                "\nFecha retorno: " + v.fechaRegreso +
                                                "\nCosto vuelo: " + v.costo +
                                                "\nCliente: " + v.cliente!!.nombres +
                                                "\nGuia: " + v.guia!!.nombres+
                                        "************************************\n"
                            )})
                        }
                        3->
                            banderaG = false
                    }
                }
            }
            4 ->{
                println("Telefono-destino")
                vuelos.forEach({v -> println(
                    "Telefono: "+ v!!.cliente!!.telefono +
                            "\n Destino: "+ v.destino
                )})
            }
            5 -> {
                //Persistencia
                writeClientes("/home/jpizarro/AndroidStudioProjects/Ejercicio/app/src/main/java/com/example/ejercicio/clientes.txt")
                writeGuias("/home/jpizarro/AndroidStudioProjects/Ejercicio/app/src/main/java/com/example/ejercicio/guias.txt")
                val archivoCliente = File("Clientes")

                println(despedida())
                bandera = false
            }
        }
    }
}

fun writeClientes(archivo: String){
    val fileName = archivo
    val myfile = File(fileName)
    myfile.printWriter().use { out ->
        clientes.forEach({c ->
            out.println(c.cedula +","+c.nombres+","+c.telefono)
        })
    }
}
fun writeGuias(archivo: String){
    val fileName = archivo
    val myfile = File(fileName)

    myfile.printWriter().use { out ->
        guias.forEach({c ->
            out.println(c.cedula +","+c.nombres+","+c.telefono+","+c.descripcion+","+c.status)
        })
    }
}
fun menu(): String {
    return "****************************************** \n" +
            "\t \t Menu Principal \n" +
            "****************************************** \n" +
            "\t [1] Menu Clientes \n" +
            "\t [2] Menu Guias \n" +
            "\t [3] Vuelos \n" +
            "\t [4] Obtener telefono-destino \n"+
            "\t [5] Salir\n"
}

fun menuDefault(description:String): String {
    return "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t \t Menu $description \n" +
            "+++++++++++++++++++++++++++++++++++++++++++ \n" +
            "\t [1] Agregar $description \n" +
            "\t [2] Listar $description \n" +
            "\t [3] Atras"
}

fun despedida():String{
    return "******************************************* \n" +
            "\t Gracias por usar nuestro sistema" +
            "******************************************* \n" +
            "Realizaro por: Jorge Pizarro \n" +
            "Universidad Politécnica Salesiana"
}