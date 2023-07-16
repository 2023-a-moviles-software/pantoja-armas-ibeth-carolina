package modelos

import java.time.LocalDate

class ModeloAuto(
    private val id: String,
    private val nombreMod: String,
    private val precio: Double,
    private val fuerzaMotor: Double,
    private val unidadesDisponibles: Int,
    private val esSedan: Boolean,
    private val fechaLanzamiento: LocalDate,
    private val marca: Marca)
{
    constructor() : this("", "",0.0, 0.0, 0, false, LocalDate.now(), Marca())

    public fun getId(): String {
        return id
    }

    public fun getNombreMod(): String {
        return nombreMod
    }

    fun getPrecio(): Double {
        return precio
    }

    fun getFuerzaMotor(): Double {
        return fuerzaMotor
    }

    fun getUnidadesDisponibles(): Int {
        return unidadesDisponibles
    }

    fun getEsSedan(): Boolean {
        return esSedan
    }

    fun getFechaLanzamiento(): LocalDate {
        return fechaLanzamiento
    }

    fun getMarca(): Marca {
        return marca
    }

    override fun toString(): String {
        return "$id, $nombreMod, $precio, $fuerzaMotor, $unidadesDisponibles, $esSedan, $fechaLanzamiento, ${marca.getId()}"

    }

    fun getListaDatos(): List<String>{
        return listOf(
            "Nombre del modelo: $nombreMod",
            "Precio: $precio",
            "Fuerza del motor: $fuerzaMotor",
            "Unidades disponibles: $unidadesDisponibles",
            "Es sedan?: $esSedan",
            "Fecha de lanzamiento: $fechaLanzamiento",
            "Marca del automovil: ${marca.getNombre()}"
        )
    }



}