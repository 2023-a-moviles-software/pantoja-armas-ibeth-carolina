package vistas

import controlador.MarcaControlador
import controlador.ModeloAutoControlador
import datos.ModeloAutoDato
import modelos.ModeloAuto
import java.time.LocalDate

class ModeloAutoVista {

    fun opcionesModeloAutoMenu(parent: mainVista) {
        var regresar = false
        do {
            println("Seleccionar una opción:")
            println("1. Crear modelo de un auto")
            println("2. Ver modelos")
            println("3. Modificar modelos")
            println("4. Eliminar modelo")
            println("5. Regresar")

            val opcion = readln().toInt()
            when (opcion) {
                1 -> crearModelo()
                2 -> verModelo()
                3 -> modificarModelo()
                4 -> eliminarModelo()
                5 -> {
                    regresar = true
                    parent.run()
                }
            }
        }while (!regresar)
    }

    fun crearModelo() {

        println("Ingresar el nombre del modelo de automovil:")
        val nombreMod = readln()
        println("Ingresar el precio:")
        val precio = readln().toDouble()
        println("Ingresar el cilindraje en litros :")
        val fuerzaMotor = readln().toDouble()
        println("Ingresar las unidades disponibles del modelo: ")
        val unidadesDisponibles = readln().toInt()
        println("El auto es de tipo Sedan? Si(S) o No(N)")
        val esSedan = readln().uppercase() == "S"
        println("Cual fue la fecha de lanzamiento? (año-mes-dia)")
        val fechaLanzamiento = LocalDate.parse(readln())
        println("Seleccionar la marca del modelo de automovil")
        val marcas = MarcaControlador.getInstance().safeGetAll()
        marcas.forEachIndexed { index, marca ->
            println("${index + 1}. ${marca.getNombre()}")
        }
        val marcaIndex = readln().toInt() - 1
        val marca = marcas[marcaIndex]

        val modelosAutos = ModeloAutoDato(
            nombreMod = nombreMod,
            precio = precio,
            fuerzaMotor = fuerzaMotor,
            unidadesDisponibles = unidadesDisponibles,
            esSedan = esSedan,
            fechaLanzamiento = fechaLanzamiento,
            marca = marca)

        val crearrModelosAutos = ModeloAutoControlador.getInstance().create(modelosAutos)
        marca.agregarModelo(crearrModelosAutos)
        MarcaControlador.getInstance().update(marca)


        println("Se registro el modelo de automovil correctamente")

    }
    fun verModelo() {
        val modelosAutos: List<ModeloAuto> = ModeloAutoControlador.getInstance().safeGetAll()

        if (modelosAutos.isEmpty()) {
            println("No existen modelos registrados")
        } else {
            modelosAutos.forEach {
                println(it.getListaDatos()) }
        }
    }

    fun modificarModelo(){
        val modelosAutos = ModeloAutoControlador.getInstance().safeGetAll()
        modelosAutos.forEachIndexed { index, it -> println("${index + 1}. ${it.getNombreMod()}") }
        println("De que modelo desea modificar la informacion?")
        val opcion = readln().toInt()
        if (opcion > modelosAutos.size || opcion < 1) { println("La opcion no es correcta")
            return
        }
        val nuevosDatosModelo = modelosAutos[opcion - 1]
        println("Ingresar la nueva informacion: ")
        println("Ingresar el nombre del modelo de automovil:")
        val nombreMod = readln()
        println("Ingresar el precio:")
        val precio = readln().toDouble()
        println("Ingresar el cilindraje en litros :")
        val fuerzaMotor = readln().toDouble()
        println("Ingresar las unidades disponibles del modelo: ")
        val unidadesDisponibles = readln().toInt()
        println("El auto es de tipo Sedan? Si(S) o No(N)")
        val esSedan = readln().uppercase() == "S"
        println("Cual fue la fecha de lanzamiento? (año-mes-dia)")
        val fechaLanzamiento = LocalDate.parse(readln())
        println("Seleccionar la marca del modelo de automovil")
        val marcas = MarcaControlador.getInstance().safeGetAll()
        marcas.forEachIndexed { index, marca -> println("${index + 1}. ${marca.getNombre()}")
        }

        val marcaIndex = readln().toInt()

        if (marcaIndex > marcas.size || marcaIndex < 1) { println("La opcion no es correcta")
            return
        }

        val marca = marcas[marcaIndex - 1]

        val nuevosdatos = ModeloAuto(
            id = nuevosDatosModelo.getId(),
            nombreMod = nombreMod,
            precio = precio,
            fuerzaMotor = fuerzaMotor,
            unidadesDisponibles = unidadesDisponibles,
            esSedan = esSedan,
            fechaLanzamiento = fechaLanzamiento,
            marca = marca)

        val modeloNuevo = ModeloAutoControlador.getInstance().update(nuevosdatos)

        if (modeloNuevo == null) { println("No se pueden modificar los datos del modelo")
            return
        }

        marca.removerModelo(nuevosDatosModelo)
        marca.agregarModelo(modeloNuevo)
        MarcaControlador.getInstance().update(marca)

        nuevosdatos.getListaDatos()
        println("Datos del modelo de automovil se modificaron exitosamente")





    }
    fun eliminarModelo(){
        val modelosAutos = ModeloAutoControlador.getInstance().safeGetAll()

        modelosAutos.forEachIndexed { index, it -> println("${index + 1}. ${it.getNombreMod()}") }
        println("De que modelo desea eliminar la informacion?")
        val opcion = readln().toInt()
        if (opcion > modelosAutos.size || opcion < 1) { println("La opcion no es correcta")
            return
        }
        val modelosAutosSel = modelosAutos[opcion - 1]
        val marca = modelosAutosSel.getMarca()
        marca.removerModelo(modelosAutosSel)
        MarcaControlador.getInstance().update(marca)
        ModeloAutoControlador.getInstance().remove(modelosAutosSel.getId())
        println("El modelo se elimino exitosamente")

    }




}
