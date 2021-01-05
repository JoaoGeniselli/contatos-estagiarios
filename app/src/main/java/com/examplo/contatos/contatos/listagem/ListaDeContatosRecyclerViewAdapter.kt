package com.examplo.contatos.contatos.listagem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examplo.contatos.Contato
import com.examplo.contatos.R

typealias ContatoSelecionado = (Contato) -> Unit

class ListaDeContatosRecyclerViewAdapter(
    private val contatos: List<Contato>,
    private val contatoSelecionado: ContatoSelecionado
) : RecyclerView.Adapter<ListaDeContatosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaDeContatosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_de_contatos_item, parent, false)
        return ListaDeContatosViewHolder(view, contatoSelecionado)
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(holder: ListaDeContatosViewHolder, position: Int) {
        holder.bind(contato = contatos[position])
    }
}