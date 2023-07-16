package vistas

import controlador.MarcaControlador
import datos.MarcaDato
import modelos.Marca
import modelos.ModeloAuto

class MarcaVista {

    fun opcionesMarcaMenu(parent: mainVista){
        var regresar = false
        do{
            println("Seleccionar una opcion")
            println("1. Crear una marca")
            println("2. Ver marcas")
            println("3. Modificar una marca")
            println("4. Eliminar una marca")
            println("5. Regresar")

            val opcion = readln().toInt()
            when(opcion) {
                1 -> crearMarca()
                2 -> verMarca()
                3 -> modificarMarca()
                4 -> eliminarMarca()
                5 -> {
                    regresar = true
                    parent.run()
                }
            }
        }while (regresar)
    }
    //Una marca tiene muchos modelos de automoviles
    fun crearMarca(){
        println("Ingresar el nombre de la marca:")
        val nombre = readln()
        println("Ingresar el pais de origen:")
        val pais = readln()
        println("Ingresar la ciudad donde se encuentra la matriz:")
        val ciudad = readln()
        println("Ingresar la cantidad de consesionarios hay en la cuidad: ")
        val concesionarios = readln()

        val marca = MarcaDato(
            nombre = nombre,
            pais = pais,
            ciudad = ciudad,
            concesionarios = concesionarios,
            modelosAutos = listOf<ModeloAuto>())

        MarcaControlador.getInstance().create(marca)



        println("La marca se registro extosamente")
    }

    fun verMarca(){
        val marcas = MarcaControlador.getInstance().safeGetAll()
        if(marcas.isEmpty()){
            println("No existen marcas registradas")
        }else{
            marcas.forEach{
                println(it.getListaDatos())
            }
        }
    }

    fun modificarMarca(){
        val marcas= MarcaControlador.getInstance().safeGetAll()
        if(marcas.isEmpty()){ println("No existen marcas registradas")
            return }
        marcas.forEachIndexed { index, it -> println("${index + 1}. ${it.getNombre()}") }
        println("De que marca desea modificar la informacion?")
        val opcion = readln().toInt()
        if (opcion > marcas.size || opcion < 1) { println("La opcion no es correcta")
            return }

        val nuevosDatosMarca = marcas[opcion-1]
        println("Ingresar la nueva informacion: ")
        println("Nombre:")
        val nombre = readln()
        println("Ingresar el pais de origen:")
        val pais = readln()
        println("Ingresar la ciudad donde se encuentra la matriz:")
        val ciudad = readln()
        println("Ingresar la cantidad de consesionarios hay en la cuidad: ")
        val concesionarios = readln()

        val marca = Marca(
            id = nuevosDatosMarca.getId(),
            nombre = nombre,
            pais = pais,
            ciudad = ciudad,
            concesionarios = concesionarios,
            modelosAutos = nuevosDatosMarca.getModelosAutos()
        )

        val modificarrMarca = MarcaControlador.getInstance().update(marca)
        if (modificarrMarca==null){ println("No se pueden modificar los datos de la marca")
            return}
        println("Datos de la marca se modificaron exitosamente")
    }

    fun eliminarMarca(){
        val marca = MarcaControlador.getInstance().safeGetAll()
        if (marca.isEmpty()) { println("No existen marcas registradas")
            return
        }
        marca.forEachIndexed { index, it -> println("${index + 1}. ${it.getNombre()}") }
        println("De que marca desea eliminar la informacion?")
        val opcion = readln().toInt()
        if (opcion > marca.size || opcion < 1) { println("La opcion no es correcta")
            return }
        val marcaAEliminar = marca[opcion - 1]
        if (marcaAEliminar.getModelosAutos().isNotEmpty()) { println("No se puede eliminar esta marca")
            return }
        val id = marcaAEliminar.getId()
        MarcaControlador.getInstance().remove(id)
        println("La marca se elimino exitosamente")
    }
}
