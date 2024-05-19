package com.kanna.agendatarea

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista: RecyclerView = findViewById(R.id.lista)
        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);

        var personarepo = PersonaRepositorio()
        var listaPersonas = personarepo.listar()

        val adapter = AdaptadorPersonas(listaPersonas as ArrayList<Persona>)
        lista.adapter = adapter

        findViewById<FloatingActionButton>(R.id.btnAgregar)
            .setOnClickListener{
                val intent = Intent(this, RegistroPersonas::class.java)
                startActivity(intent)
            }
    }
}