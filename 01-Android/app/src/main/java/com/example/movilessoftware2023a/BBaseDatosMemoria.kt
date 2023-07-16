package com.example.movilessoftware2023a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Betsabé", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Abigail", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Brandon", "c@c.com")
                )
        }
    }
}