package controlador

import datos.MarcaDato
import modelos.Marca
import java.io.File

class MarcaControlador {
    private val file: File = File ("src/main/resources/MarcaControlador.txt").also {if(!it.exists()){it.createNewFile()}}

    companion object {
        private var instance: MarcaControlador? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MarcaControlador().also { instance = it }
        }
    }

    private fun randomString(): String {
        val chars = ('a'..'z') +
                ('A'..'Z') +
                ('0'..'9')
        return (1..10).map {chars.random()}.joinToString("")
    }
    //Crear
    fun create(marca: MarcaDato): Marca {
        val newMarca = Marca(
            randomString(),
            marca.nombre,
            marca.pais,
            marca.ciudad,
            marca.concesionarios,
            marca.modelosAutos.toMutableList()
        )
        file.appendText(newMarca.toString() + "\n")
        return newMarca
    }


    fun getAll(): List<Marca> {
        val lines = file.readLines()
        val marca = lines.map{ it ->
            val marcaSplit = it.split(",")
            val modelosAutos = marcaSplit[5].split(";").map { ModeloAutoControlador.getInstance().getOne(it) }

            val validModelosAutos = modelosAutos.filterNotNull()
            return@map Marca(
                marcaSplit[0],
                marcaSplit[1],
                marcaSplit[2],
                marcaSplit[3],
                marcaSplit[4],
                validModelosAutos.toMutableList()
            )
        }
        return marca
    }

    fun safeGetAll(): List<Marca> {
        val lines = file.readLines()
        val marca = lines.map { it -> val marcaSplit = it.split(",")
            val modelosAutos = marcaSplit[5].split(";").map { ModeloAutoControlador.getInstance().getOneWithoutMarca(it) }
            val validModelosAutos = modelosAutos.filterNotNull()
            return@map Marca(
                marcaSplit[0],
                marcaSplit[1],
                marcaSplit[2],
                marcaSplit[3],
                marcaSplit[4],
                validModelosAutos.toMutableList())
        }
        return marca
    }

    fun getOne(id: String): Marca? {
        val lines = file.readLines()
        val marcaString = lines.find { it.split(",")[0] == id } ?: return null

        val marcaSplit = marcaString.split(",")
        val modelosAutos  = marcaSplit[5].split(";").map{ModeloAutoControlador.getInstance().getOne(it)}

        val validModelosAutos = modelosAutos.filterNotNull()
        return Marca(
            marcaSplit[0],
            marcaSplit[1],
            marcaSplit[2],
            marcaSplit[3],
            marcaSplit[4],
            validModelosAutos.toMutableList())
    }

    fun getOneWithModelosWithoutMarcas(id: String): Marca? {
        val lines = file.readLines()
        val marcaString = lines.find { it.split(",")[0] == id } ?: return null

        val marcaSplit = marcaString.split(",")
        val modelosAutos = marcaSplit[5].split(";").map { ModeloAutoControlador.getInstance().getOneWithoutMarca(it) }

        val validModelosAutos = modelosAutos.filterNotNull()
        return Marca(
            marcaSplit[0],
            marcaSplit[1],
            marcaSplit[2],
            marcaSplit[3],
            marcaSplit[4],
            validModelosAutos.toMutableList())
    }

    //modificar
    fun update(marca: Marca): Marca? {
        val lines = file.readLines()
        val marcaString = lines.find { it.split(",")[0] == marca.getId() } ?:
        return null

        val marcaSplit = marcaString.split(",")

        val newMarca = Marca(
            marcaSplit[0],
            marca.getNombre(),
            marca.getPais(),
            marca.getCiudad(),
            marca.getConcesionarios(),
            marca.getModelosAutos())

        this.remove(marca.getId())

        file.appendText(newMarca.toString() + "")

        return newMarca
    }

    fun remove(id: String) {
        file.readLines()
            .filter { it -> it.split(",")[0] != id }
            .joinToString("\n", postfix = "\n")
            .also { file.writeText(it) }
    }

   }