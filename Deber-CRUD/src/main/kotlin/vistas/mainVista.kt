package vistas

import controlador.MarcaControlador
import modelos.Marca
import modelos.ModeloAuto


class mainVista {
    private val marcaVista = MarcaVista()
    private val modeloAutoVista = ModeloAutoVista()

    fun run(){
        println("MARCAS - AUTOS")
        opcionesMenu()
    }

    fun opcionesMenu(){
        var repetirMenu = false
        do{
            println("Seleccionar una opcion")
            println("1. Modelos de automoviles")
            println("2. Marcas")
            println("3. Salir")

            val opcion = readln().toInt()
            when (opcion){
                1 -> {
                    if(MarcaControlador.getInstance().safeGetAll().isEmpty()){
                        println("No existen automoviles para mostar su marca")
                        repetirMenu = true
                    } else{
                        modeloAutoVista.opcionesModeloAutoMenu(this)
                    }
                }
                2-> marcaVista.opcionesMarcaMenu(this)
                3-> println("Gracias!")
            }
        }while (!repetirMenu)

    }
}