package com.kanna.agendatarea

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroPersonas : AppCompatActivity() {
    var edita: Boolean = false
    var id: Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_personas)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{

            val nombre = findViewById<TextView>(R.id.txtNombre).text.toString()
            val correo = findViewById<TextView>(R.id.txtCorreo).text.toString()
            val telefono = findViewById<TextView>(R.id.txtTelefono).text.toString()
            val observaciones = findViewById<TextView>(R.id.txtObservaciones).text.toString()

            if(nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || observaciones.isEmpty()){
                Toast.makeText(this, "Algunos campos estan vacios",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (edita){
                var personarepo = PersonaRepositorio()
                personarepo.actualizar(id, nombre, correo, telefono, observaciones)
            }else{
                var personarepo = PersonaRepositorio()
                personarepo.crear(nombre, correo, telefono, observaciones)
            }
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        var recibidos:Bundle? = intent.extras
        if (recibidos != null){
            var persona = recibidos?.get("persona") as Persona
            edita = true
            id = persona.id!!
            var txtNombre = findViewById<TextView>(R.id.txtNombre)
            var txtCorreo = findViewById<TextView>(R.id.txtCorreo)
            var txtTelefono = findViewById<TextView>(R.id.txtTelefono)
            var txtObservaciones = findViewById<TextView>(R.id.txtObservaciones)
            txtNombre.setText(persona.nombre)
            txtCorreo.setText(persona.correo)
            txtTelefono.setText(persona.telefono)
            txtObservaciones.setText(persona.observaciones)
        }
    }
}