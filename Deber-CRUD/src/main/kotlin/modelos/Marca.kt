package modelos


class Marca(
    private val id: String,
    private val nombre: String,
    private val pais: String,
    private val ciudad: String,
    private val concesionarios: String,
    private var modelosAutos: MutableList<ModeloAuto>)
{
    constructor(): this("", "", "", "","", mutableListOf())

    public fun getId(): String {
        return id
    }

    fun getNombre(): String {
        return nombre
    }

    fun getPais(): String {
        return pais
    }

    fun getCiudad(): String {
        return ciudad
    }

    fun getConcesionarios(): String {
        return concesionarios
    }

    fun getModelosAutos(): MutableList<ModeloAuto>{
        return modelosAutos
    }

    fun agregarModelo(modelosAutos: ModeloAuto){
        this.modelosAutos.add(modelosAutos)
    }

    fun removerModelo(modelosAutos: ModeloAuto){
        this.modelosAutos = this.modelosAutos.filter{it.getId() != modelosAutos.getId()}.toMutableList()

    }

    override fun toString(): String {
        val ids: String = modelosAutos.map { it.getId()}.joinToString(";")
        return "$id, $nombre, $pais, $ciudad, $concesionarios, $ids"

    }

    fun getListaDatos(): List<String>{
        return listOf(
            "Nombre de la marca: $nombre",
            "Pais de origen: $pais",
            "Ciudad en la que se encuentra la matriz: $ciudad",
            "Numero de concesionarios: $concesionarios",
            "Modelos: ${modelosAutos.map { it.getNombreMod() }}"

        )
    }

}