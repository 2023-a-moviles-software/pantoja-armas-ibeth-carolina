package com.example.movilessoftware2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FReciclerViewAdapterNombreCedula (
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FReciclerViewAdapterNombreCedula.MyViewHolder>() {
        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            //Logica nuestra
            val nombreTextView: TextView
            val cedulaTextView: TextView
            val likesTextView: TextView
            val accionBoton: Button
            var numeroLikes = 0
            init {
                nombreTextView = view.findViewById(R.id.tv_nombre)
                cedulaTextView = view.findViewById(R.id.tv_cedula)
                likesTextView = view.findViewById(R.id.tv_likes)
                accionBoton = view.findViewById(R.id.btn_dar_like)
                accionBoton.setOnClickListener{ anadirLike() }
            }
            fun anadirLike(){
                numeroLikes = numeroLikes + 1
                likesTextView.text = numeroLikes.toString()
                contexto.aumentarTotalLikes()
            }
        }
    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    //Setar Datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorEActual = this.lista[position]
        holder.nombreTextView.text = entrenadorEActual.nombre
        holder.cedulaTextView.text = entrenadorEActual.descripcion
        holder.accionBoton.text = "Like ${entrenadorEActual.id} - ${entrenadorEActual.nombre}"
        //En el valor que tiene iniciaria
        holder.likesTextView.text = "0"
    }

    //Tomando el arrreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}