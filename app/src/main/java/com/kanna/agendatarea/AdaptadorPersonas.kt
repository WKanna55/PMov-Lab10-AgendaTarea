package com.kanna.agendatarea

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class AdaptadorPersonas (val ListaPersonas:ArrayList<Persona>):
    RecyclerView.Adapter<AdaptadorPersonas.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fTelefono = itemView.findViewById<TextView>(R.id.lblTelefono)
        val fEliminar = itemView.findViewById<Button>(R.id.btnEliminar)
        val fEditar = itemView.findViewById<Button>(R.id.btnEditar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).
        inflate(R.layout.vista_persona, parent, false);
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var person = this.ListaPersonas.get(position)
        holder?.fNombre?.text = ListaPersonas[position].nombre
        holder?.fTelefono?.text = ListaPersonas[position].telefono
        holder?.fEliminar?.setOnClickListener(){
            var personrepo = PersonaRepositorio()
            personrepo.borrar(person.id!!)
            this.ListaPersonas.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position, itemCount)
        }
        holder?.fEditar?.setOnClickListener(){
            var personrepo = PersonaRepositorio()
            var persona = personrepo.leer(person.id!!)

            var editarPersona = Intent(holder?.itemView?.context, RegistroPersonas::class.java)
            editarPersona.putExtra("persona", persona as Serializable)
            holder?.itemView?.context?.startActivity(editarPersona)
            //itemView = fEditar -> si falla
        }
    }
    override fun getItemCount(): Int {
        return ListaPersonas.size
    }
}