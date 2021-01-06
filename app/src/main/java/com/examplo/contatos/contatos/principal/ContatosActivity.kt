package com.examplo.contatos.contatos.principal

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.examplo.contatos.R
import com.examplo.contatos.contatos.listagem.ListaDeContatosFragment
import com.examplo.contatos.contatos.utilidades.GerenciadorDePermissoes

private const val CODIGO_REQUISICAO_PERMISSAO_LEITURA_CONTATOS = 1222

class ContatosActivity : AppCompatActivity() {

    private val gerenciadorDePermissoes by lazy {
        GerenciadorDePermissoes(
            codigoDeRequisicaoDePermissoes = CODIGO_REQUISICAO_PERMISSAO_LEITURA_CONTATOS,
            permissoesParaChecar = listOf(Manifest.permission.READ_CONTACTS),
            fluxoPermissoesConcedidas = { montarListaDeContatos() },
            fluxoPermissoesNegadas = { finish() }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)
        gerenciadorDePermissoes.executarFluxoDePermissoes(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        gerenciadorDePermissoes.validarPermissoesDepoisDeSolicitarAoUsuario(this)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.adicionar == item.itemId) {
            iniciarFluxoDeCriacaoDeContatos()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarFluxoDeCriacaoDeContatos() {
        val intent = Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI)
        startActivity(intent)
    }

    private fun montarListaDeContatos() {
        supportFragmentManager.commit {
            replace<ListaDeContatosFragment>(R.id.fragment_container_view)
        }
    }
}