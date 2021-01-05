package com.examplo.contatos

abstract class APIContatos {

    abstract fun listarContatos(): List<Contato>
    abstract fun adicionarContato(nome: String, telefone: String)

    companion object {

        fun obterAPIOficial(): APIContatos {
            return APIContatosDoAndroid()
        }

        fun obterAPISimulada(): APIContatos {
            return APIContatosSimulada()
        }
    }
}