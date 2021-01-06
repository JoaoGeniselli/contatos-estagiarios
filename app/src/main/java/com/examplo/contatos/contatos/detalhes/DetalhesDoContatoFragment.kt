package com.examplo.contatos.contatos.detalhes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.examplo.contatos.CONTATO_EXTRA_ID
import com.examplo.contatos.Contato
import com.examplo.contatos.R
import kotlinx.android.synthetic.main.fragment_detalhes_do_contato.*

class DetalhesDoContatoFragment : Fragment(R.layout.fragment_detalhes_do_contato) {

    private val contatoDetalhes by lazy {
        requireArguments().getSerializable(CONTATO_EXTRA_ID) as Contato
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurarFotoDoContato()
        configurarNomeDoContato()
        configurarTelefoneDoContato()
    }

    private fun configurarFotoDoContato() {
        image_view_foto_contato.setImageResource(contatoDetalhes.foto)
    }

    private fun configurarNomeDoContato() {
        text_view_nome_contato.text = contatoDetalhes.nome
    }

    private fun configurarTelefoneDoContato() {
        text_view_numero_contato.text = contatoDetalhes.telefone
    }

}