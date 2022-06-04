package ec.edu.ups.moviles.controlador
import ec.edu.ups.moviles.modelo.Autor
import ec.edu.ups.moviles.modelo.CopiaLibro
import ec.edu.ups.moviles.modelo.Lector
import ec.edu.ups.moviles.modelo.Prestamo
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat

class Lectores {
    var lectores = mutableListOf<Lector>()
    fun agregarLector(lector: Lector) = lectores.add(lector)
    fun eliminarLector(lector: Lector) = lectores.remove(lector)
    fun cargarLectores() {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Lectores.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(",").toTypedArray()
                val l = Lector(data[0],data[1])
                agregarLector(l)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarLectores(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Lectores.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            lectores.forEach({d ->
                out.println(d.nombre +","+d.cedula)
            })
        }
    }
}

class CopiasLibro {
    var copiasLibro = mutableListOf<CopiaLibro>()
    val formato = SimpleDateFormat("dd/MM/yyyy")
    fun agregarCopia(copiaLibro: CopiaLibro) = copiasLibro.add(copiaLibro)
    fun eliminarCopia(copiaLibro: CopiaLibro) = copiasLibro.remove(copiaLibro)
    fun cargarCopias() {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Copias.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(",").toTypedArray()
                var autor = Autor(data[5],data[6],formato.parse(data[7]))
                var c = CopiaLibro(data[0].toInt(),data[1],data[2],data[3],data[4].toInt(), autor, data[8])
                agregarCopia(c)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarCopias(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Copias.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            copiasLibro.forEach({d ->
                out.println(d.codigo.toString() +","+
                            d.nombre +","+
                            d.tipo+","+
                            d.editorial+","+
                            d.year+","+
                            d.autor.nombre+","+
                            d.autor.nacionalidad+","+
                            formato.format(d.autor.fechaNacimiento)+","+
                            d.estado)
            })
        }
    }
}

class Prestamos{
    var prestamos = mutableListOf<Prestamo>()
    val formato = SimpleDateFormat("dd/MM/yyyy")
    fun agregarPrestamo(prestamo: Prestamo) = prestamos.add(prestamo)
    fun eliminarPrestamo(prestamo: Prestamo) = prestamos.remove(prestamo)
    fun cargarPrestamos(ctlCopias: CopiasLibro, ctlLectores: Lectores) {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Prestamos.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(",").toTypedArray()
                var copia: CopiaLibro = ctlCopias.copiasLibro.first(){c->c.codigo==data[1].toInt()}
                var lector: Lector = ctlLectores.lectores.first(){l->l.cedula==data[4]}
                var prestamo: Prestamo;
                if(data[3]=="null"){
                    prestamo = Prestamo(data[0].toInt(),copia,formato.parse(data[2]),null,lector)
                }else{
                    prestamo = Prestamo(data[0].toInt(),copia,formato.parse(data[2]),formato.parse(data[3]),lector)
                }

                agregarPrestamo(prestamo)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarPrestamos(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/Prueba02/app/src/main/java/ec/edu/ups/moviles/controlador/Prestamos.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            prestamos.forEach({d ->
                out.println(
                        d.codigo.toString() + ","+
                        d.copiaLibro.codigo.toString() + ","+
                        formato.format(d.fechaSalida)+","+
                        try{formato.format(d.fechaRegreso)}
                        catch (e:Exception){"null"}+","+
                        d.lector.cedula)
            })
        }
    }
}