package datos

import modelos.ModeloAuto

class MarcaDato(
    val nombre: String,
    val pais: String,
    val ciudad: String,
    val concesionarios: String,
    val modelosAutos: List<ModeloAuto>
) {
}