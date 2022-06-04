package ec.edu.ups.moviles.controlador
import ec.edu.ups.moviles.modelo.*
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
class Series {
    var series = mutableListOf<Serie>()
    fun agregarSerie(s: Serie) = series.add(s)
    fun cargarSeries(ctlTemporada: Temporadas, ctlPersonajes: Personajes) {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Serie.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(";").toTypedArray()
                var temporadasCodigos = data[5].split("-").toTypedArray().toMutableList()
                var personajesCodigos = data[6].split("-").toTypedArray().toMutableList()
                var temporadas = mutableListOf<Temporada>()
                temporadasCodigos.forEach { codigo ->
                    temporadas.add(ctlTemporada.temporadas.first { temporada -> temporada.codigo==codigo.toInt()})
                }
                var personajes = mutableListOf<Personaje>()
                personajesCodigos.forEach { codigo ->
                    personajes.add(ctlPersonajes.personajes.first { personaje -> personaje.codigo==codigo.toInt()})
                }
                var serie = Serie(data[0].toInt(),data[1],data[2].toInt(),data[3],data[4],temporadas,personajes)
                agregarSerie(serie)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarCapitulos(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Serie.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            series.forEach({t ->
                out.println(t.codigo.toString() +";"+ t.tituloSerie+";"+ t.anio.toString() + ";" + t.sinopsisSerie + ";"+ t.genero +";"
                        + t.temporadas.forEach { capitulo -> capitulo.codigo.toString()+"-" }
                        + t.personajes.forEach { capitulo -> capitulo.codigo.toString()+"-" })
            })
        }
    }
}
class Temporadas {
    var temporadas = mutableListOf<Temporada>()
    val formato = SimpleDateFormat("dd/MM/yyyy")
    fun agregarTemporada(t: Temporada) = temporadas.add(t)
    fun cargarTemporadas(ctlCapitulos: Capitulos) {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Temporadas.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(";").toTypedArray()
                var capitulosCodigos = data[4].split("-").toTypedArray().toMutableList()
                var capitulos = mutableListOf<Capitulo>()
                capitulosCodigos.forEach { codigo ->
                    capitulos.add(ctlCapitulos.capitulos.first { capitulo -> capitulo.codigo==codigo.toInt()})
                }
                var temporada = Temporada(data[0].toInt(),data[1].toInt(),formato.parse(data[2]),formato.parse(data[3]),capitulos)
                agregarTemporada(temporada)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarCapitulos(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Temporadas.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            temporadas.forEach({t ->
                out.println(t.codigo.toString() +";"+ t.numeroTemporada.toString() +";"+ formato.format(t.fechaProduccion) +";"+formato.format(t.fechaLanzamiento)
                        + t.capitulos.forEach { capitulo -> capitulo.codigo.toString()+"-" })
            })
        }
    }
}
class Capitulos {
    var capitulos = mutableListOf<Capitulo>()
    fun agregarCapitulo(c: Capitulo) = capitulos.add(c)
    fun cargarCapitulos(ctlActores: Actores) {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Capitulos.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->

                var data = line.split(";").toTypedArray()
                var actoresCodigos = data[4].split("-").toTypedArray().toMutableList()
                var actores = mutableListOf<Actor>()

                actoresCodigos.forEach { codigo ->
                    actores.add(ctlActores.actores.first { actor -> actor.codigo==codigo.toInt()})
                }
                var capitulo = Capitulo(data[0].toInt(),data[1],data[2],data[3],actores)
                agregarCapitulo(capitulo)

            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarCapitulos(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Capitulos.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            capitulos.forEach({c ->
                out.println(c.codigo.toString() +";"+ c.tituloCapitulo +";"+ c.duracion+";"+c.sinopsis
                + c.actores.forEach { actor -> actor.codigo.toString()+"-" })
            })
        }
    }
}
class Personajes {
    var personajes = mutableListOf<Personaje>()
    fun agregarPersonaje(p: Personaje) = personajes.add(p)
    fun cargarPersonajes(ctlActores: Actores) {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Personajes.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(";").toTypedArray()
                var actor = ctlActores.actores.first { a -> a.codigo==data[2].toInt() }
                var personaje = Personaje(data[0].toInt(),data[1],actor)
                agregarPersonaje(personaje)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarPersonajes(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Personajes.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            personajes.forEach({p ->
                out.println(p.codigo.toString()+";"+p.nombre+";"+p.actor.codigo.toString())
            })
        }
    }
}
class Generos {
    var generos = mutableListOf<Genero>()
    fun agregarGenero(g: Genero) = generos.add(g)
    fun cargarGeneros() {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Generos.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(";").toTypedArray()
                var genero = Genero(data[0].toInt(),data[1])
                agregarGenero(genero)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarActores(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Generos.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            generos.forEach({g ->
                out.println(g.codigo.toString()+";"+g.descripcion)
            })
        }
    }
}
class Actores {
    var actores = mutableListOf<Actor>()
    fun agregarLector(actor: Actor) = actores.add(actor)
    fun cargarActores() {
        try{
            val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Actores.csv"
            var lines:List<String> = File(fileName).readLines()
            lines.forEach{ line ->
                var data = line.split(";").toTypedArray()
                var actor = Actor(data[0].toInt(),data[1])
                agregarLector(actor)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun guardarActores(){
        val fileName = "/home/jpizarro/AndroidStudioProjects/ExamenApp/app/src/main/java/ec/edu/ups/moviles/controlador/Actores.csv"
        val myfile = File(fileName)
        myfile.printWriter().use { out ->
            actores.forEach({a ->
                out.println(a.codigo.toString()+";"+a.nombre)
            })
        }
    }
}