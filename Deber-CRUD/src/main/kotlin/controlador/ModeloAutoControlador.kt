package controlador

import datos.ModeloAutoDato
import modelos.Marca
import modelos.ModeloAuto
import java.io.File
import java.time.LocalDate

class ModeloAutoControlador {
    private val file: File = File ("src/main/resources/ModelosAutos.txt").also {if(!it.exists()){it.createNewFile()}}

    companion object {
        private var instance: ModeloAutoControlador? = null;

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ModeloAutoControlador().also { instance = it }
        }
    }

    private fun randomString(): String {
        val chars = ('a'..'z') +
                ('A'..'Z') +
                ('0'..'9')
        return (1..10).map { chars.random() }.joinToString("")
    }


    fun safeGetAll(): List<ModeloAuto> {
        val lines = file.readLines()
        val modelosAutos= lines.map { it -> val modelosAutosSplit = it.split(",")
            val marca = MarcaControlador.getInstance().getOneWithModelosWithoutMarcas(modelosAutosSplit[7]) ?: return@map null

            return@map ModeloAuto(
                modelosAutosSplit[0],
                modelosAutosSplit[1],
                modelosAutosSplit[2].toDouble(),
                modelosAutosSplit[3].toDouble(),
                modelosAutosSplit[4].toInt(),
                modelosAutosSplit[5].toBoolean(),
                LocalDate.parse(modelosAutosSplit[6]),
                marca
            )
        }
        return modelosAutos.filterNotNull()
    }

    fun getOne(id: String): ModeloAuto? {
        val lines = file.readLines()
        val modelosString = lines.find { it.split(",")[0] == id } ?: return null

        val modelosAutosSplit = modelosString.split(",")
        val marca = MarcaControlador.getInstance().getOne(modelosAutosSplit[7]) ?: return null

        return ModeloAuto(
            modelosAutosSplit[0],
            modelosAutosSplit[1],
            modelosAutosSplit[2].toDouble(),
            modelosAutosSplit[3].toDouble(),
            modelosAutosSplit[4].toInt(),
            modelosAutosSplit[5].toBoolean(),
            LocalDate.parse(modelosAutosSplit[6]),
            marca
        )
    }

    fun getOneWithoutMarca(id: String): ModeloAuto? {
        val lines = file.readLines()
        val modelosString = lines.find { it.split(",")[0] == id } ?: return null

        val modelosAutosSplit = modelosString.split(",")
        return ModeloAuto(
            modelosAutosSplit[0],
            modelosAutosSplit[1],
            modelosAutosSplit[2].toDouble(),
            modelosAutosSplit[3].toDouble(),
            modelosAutosSplit[4].toInt(),
            modelosAutosSplit[5].toBoolean(),
            LocalDate.parse(modelosAutosSplit[6]),
            Marca()
        )
    }

    fun create(modelosAutos: ModeloAutoDato): ModeloAuto {
        val newModelos = ModeloAuto(
            id = randomString(),
            nombreMod = modelosAutos.nombreMod,
            precio = modelosAutos.precio,
            fuerzaMotor = modelosAutos.fuerzaMotor,
            unidadesDisponibles = modelosAutos.unidadesDisponibles,
            esSedan = modelosAutos.esSedan,
            fechaLanzamiento = modelosAutos.fechaLanzamiento,
            marca = modelosAutos.marca
        )
        file.appendText(newModelos.toString() + "\n")
        return newModelos
    }


    fun update(modelosAutos: ModeloAuto): ModeloAuto? {
        val lines = file.readLines()
        val modelosString = lines.find { it.split(",")[0] == modelosAutos.getId() } ?: return null

        val modelosSplit = modelosString.split(",")

        val newModelos = ModeloAuto(
            id = modelosSplit[0],
            nombreMod = modelosAutos.getNombreMod(),
            precio = modelosAutos.getPrecio(),
            fuerzaMotor = modelosAutos.getFuerzaMotor(),
            unidadesDisponibles = modelosAutos.getUnidadesDisponibles(),
            esSedan = modelosAutos.getEsSedan(),
            fechaLanzamiento = modelosAutos.getFechaLanzamiento(),
            marca = modelosAutos.getMarca()
        )

        this.remove(modelosAutos.getId())

        file.appendText(newModelos.toString() + "\n")

        return newModelos
    }

    fun remove(id: String) {
        file.readLines()
            .filter { it.split(",")[0] != id }
            .joinToString("\n", postfix = "\n")
            .also { file.writeText(it) }
    }



}