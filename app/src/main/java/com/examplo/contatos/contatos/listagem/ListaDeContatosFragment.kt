package com.examplo.contatos.contatos.listagem

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplo.contatos.APIContatos
import com.examplo.contatos.CONTATO_EXTRA_ID
import com.examplo.contatos.Contato
import com.examplo.contatos.R
import com.examplo.contatos.contatos.detalhes.DetalhesDoContatoFragment
import kotlinx.android.synthetic.main.fragment_lista_de_contatos.*

class ListaDeContatosFragment : Fragment(R.layout.fragment_lista_de_contatos) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configurarListaDeContatos()
    }

    private fun configurarListaDeContatos() {
        lista_contatos_recycler_view.adapter = configurarAdapterParaListaDeContatos()
        lista_contatos_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    private fun configurarAdapterParaListaDeContatos(): ListaDeContatosRecyclerViewAdapter {
        val listaDeContatos = obterContatos()
        return ListaDeContatosRecyclerViewAdapter(listaDeContatos) { contatoSelecionadoPeloUsuario ->
            abrirDetalhesDoContato(contatoSelecionadoPeloUsuario)
        }
    }

    private fun obterContatos(): List<Contato> {
        return APIContatos.obterAPIOficial().listarContatos()
    }

    private fun abrirDetalhesDoContato(contato: Contato) {
        val argumentos = bundleOf(CONTATO_EXTRA_ID to contato)
        parentFragmentManager.commit {
            addToBackStack(null)
            replace<DetalhesDoContatoFragment>(R.id.fragment_container_view, args = argumentos)
        }
    }

}