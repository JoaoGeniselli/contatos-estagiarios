package com.examplo.contatos

class APIContatosSimulada : APIContatos() {

    private val contatos = mutableListOf(
        Contato(
            id = "0",
            nome = "Rocky Balboa",
            telefone = "11 90000-0001",
            foto = R.drawable.icone_pessoa
        ),
        Contato(
            id = "1",
            nome = "John Rambo",
            telefone = "11 90000-0002",
            foto = R.drawable.icone_pessoa
        ),
        Contato(
            id = "2",
            nome = "Marion Cobretti (Cobra)",
            telefone = "11 90000-0003",
            foto = R.drawable.icone_pessoa
        )
    )

    override fun listarContatos(): List<Contato> = contatos

    override fun adicionarContato(nome: String, telefone: String) {
        val novoContato = Contato(
            id = contatos.size.toString(),
            nome = nome,
            telefone = telefone,
            foto = R.drawable.icone_pessoa
        )
        contatos.add(novoContato)
    }
}