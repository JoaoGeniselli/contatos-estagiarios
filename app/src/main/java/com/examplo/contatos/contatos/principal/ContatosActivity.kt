package com.examplo.contatos.contatos.principal

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.examplo.contatos.R
import com.examplo.contatos.contatos.listagem.ListaDeContatosFragment

class ContatosActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)
        montarListaDeContatos(savedInstanceState)
    }

    private fun montarListaDeContatos(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<ListaDeContatosFragment>(R.id.fragment_container_view)
            }
        }
    }
}