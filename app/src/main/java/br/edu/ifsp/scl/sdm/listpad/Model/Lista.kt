package br.edu.ifsp.scl.sdm.listpad.Model

import java.io.Serializable

class Lista (

    var id: Int? = null,
    var nome: String="",
    var descricao: String="",
    var categoria: String="",
    var quantidade: String="",
    var urgente: String="",
    var cumprido:String=""): Serializable