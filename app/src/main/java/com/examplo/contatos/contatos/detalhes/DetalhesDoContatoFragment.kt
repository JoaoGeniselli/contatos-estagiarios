package com.examplo.contatos.contatos.detalhes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.examplo.contatos.CONTATO_EXTRA_ID
import com.examplo.contatos.Contato
import com.examplo.contatos.R

class DetalhesDoContatoFragment: Fragment(R.layout.fragment_detalhes_do_contato) {

    private val contatoDetalhes by lazy {
        requireArguments().getSerializable(CONTATO_EXTRA_ID) as Contato
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Contatos", "Chegou contato: " + contatoDetalhes.nome)
    }


}