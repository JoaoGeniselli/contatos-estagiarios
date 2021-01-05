package com.examplo.contatos.contatos.listagem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.examplo.contatos.Contato
import kotlinx.android.synthetic.main.lista_de_contatos_item.view.*

class ListaDeContatosViewHolder(
    itemView: View,
    private val contatoSelecionado: ContatoSelecionado
) : RecyclerView.ViewHolder(itemView) {

    fun bind(contato: Contato) {
        itemView.image_view_foto_contato.setImageResource(contato.foto)
        itemView.text_view_nome_contato.text = contato.nome
        itemView.text_view_numero_contato.text = contato.telefone
        itemView.setOnClickListener { contatoSelecionado(contato) }
    }

}