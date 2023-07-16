package datos

import modelos.Marca
import java.time.LocalDate

class ModeloAutoDato(
    val nombreMod: String,
    val precio: Double,
    val fuerzaMotor: Double,
    val unidadesDisponibles: Int,
    val esSedan: Boolean,
    val fechaLanzamiento: LocalDate,
    val marca: Marca
) {
}