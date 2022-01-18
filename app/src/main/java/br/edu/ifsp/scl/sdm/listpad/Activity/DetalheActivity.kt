package br.edu.ifsp.scl.sdm.listpad.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.edu.ifsp.scl.sdm.listpad.Data.DatabaseHelper
import br.edu.ifsp.scl.sdm.listpad.Model.Lista
import br.edu.ifsp.scl.sdm.listpad.R

class DetalheActivity : AppCompatActivity() {

    private var lista = Lista()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        lista = this.intent.getSerializableExtra("lista") as Lista
        val nome = findViewById<EditText>(R.id.editTextNome)
        val descricao = findViewById<EditText>(R.id.editTextDescricao)
        val categoria = findViewById<EditText>(R.id.editTextCategoria)
        val urgente = findViewById<EditText>(R.id.editTextUrgente)
        val cumprido = findViewById<EditText>(R.id.editTextCumprido)
        val quantidade = findViewById<EditText>(R.id.editTextQuantidade)


        nome.setText(lista.nome)
        descricao.setText(lista.descricao)
        categoria.setText(lista.categoria)
        urgente.setText(lista.urgente)
        cumprido.setText(lista.cumprido)
        quantidade.setText(lista.quantidade)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhe, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = DatabaseHelper(this)

        if (item.itemId==R.id.action_alterarLista){
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val descricao = findViewById<EditText>(R.id.editTextDescricao).text.toString()
            val categoria = findViewById<EditText>(R.id.editTextCategoria).text.toString()
            val quantidade = findViewById<EditText>(R.id.editTextQuantidade).text.toString()
            val urgente = findViewById<EditText>(R.id.editTextUrgente).text.toString()
            val cumprido = findViewById<EditText>(R.id.editTextCumprido).text.toString()


            lista.nome = nome
            lista.descricao = descricao
            lista.categoria = categoria
            lista.quantidade = quantidade
            lista.urgente = urgente
            lista.cumprido =cumprido


            if(db.atualizarLista(lista)>0)
                Toast.makeText(this, "Informações alteradas", Toast.LENGTH_LONG).show()
            finish()
        }
        if (item.itemId==R.id.action_excluirLista){
            if (db.apagarLista(lista)>0)
                Toast.makeText(this, "Item excluído", Toast.LENGTH_LONG).show()
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}