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

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val db= DatabaseHelper(this)

        if (item.itemId==R.id.action_salvarLista){
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val descricao = findViewById<EditText>(R.id.editTextDescricao).text.toString()
            val categoria = findViewById<EditText>(R.id.editTextCategoria).text.toString()
            val quantidade = findViewById<EditText>(R.id.editTextQuantidade).text.toString()
            val urgente = findViewById<EditText>(R.id.editTextUrgente).text.toString()
            val cumprido = findViewById<EditText>(R.id.editTextCumprido).text.toString()


            val l = Lista(null, nome, descricao, categoria, quantidade, urgente, cumprido)
            if(db.inseriLista(l)>0){
                Toast.makeText(this, "Item inserido na lista", Toast.LENGTH_LONG).show()
                finish()
            }

        }
        if(item.itemId==R.id.action_sair){
            finish()

        }

        return super.onOptionsItemSelected(item)
    }
}