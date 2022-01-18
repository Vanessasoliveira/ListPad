package br.edu.ifsp.scl.sdm.listpad.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.ifsp.scl.sdm.listpad.Model.Lista

class DatabaseHelper (context: Context):
    SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object {
        private val DATABASE_NAME = "listpad.db"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "lista"
        private val ID = "id"
        private val NOME = "nome"
        private val DESCRICAO = "descricao"
        private val CATEGORIA = "categoria"
        private val QUANTIDADE = "quantidade"
        private val URGENTE = "urgente"
        private val CUMPRIDO="cumprido"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $DESCRICAO TEXT, $CATEGORIA TEXT, $QUANTIDADE TEXT, $URGENTE TEXT, $CUMPRIDO TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {


    }

    fun inseriLista(lista: Lista): Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, lista.id)
        values.put(NOME, lista.nome)
        values.put(DESCRICAO, lista.descricao)
        values.put(CATEGORIA, lista.categoria)
        values.put(QUANTIDADE, lista.quantidade)
        values.put(URGENTE, lista.urgente)
        values.put(CUMPRIDO, lista.cumprido)
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result

    }

    fun atualizarLista(lista: Lista): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOME, lista.nome)
        values.put(DESCRICAO, lista.descricao)
        values.put(CATEGORIA, lista.categoria)
        values.put(QUANTIDADE, lista.quantidade)
        values.put(URGENTE, lista.urgente)
        values.put(CUMPRIDO, lista.cumprido)
        val result = db.update(TABLE_NAME, values, "id=?", arrayOf((lista.id.toString())))
        db.close()
        return result
    }

    fun apagarLista(lista: Lista): Int {
        val db = this.writableDatabase
        val result = db.delete(
            TABLE_NAME, "id=?", arrayOf(
                lista.id
                    .toString()
            )
        )
        db.close()
        return result
    }

    fun listarLista():ArrayList<Lista>
    {
        val listaLista = ArrayList<Lista>()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $NOME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()){
            val l = Lista (cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)
                )
            listaLista.add(l)
        }
        cursor.close()
        db.close()
        return listaLista

    }


}