package com.examplo.contatos

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract

class APIContatosDoAndroid : APIContatos() {

    override fun listarContatos(): List<Contato> {
        val contatos = mutableListOf<Contato>()
        val resolver = applicationContext?.contentResolver ?: return listOf()
        val cursor = resolver.buscarPorContatos()
            ?.takeIf { it.count > 0 }
            ?: return listOf()

        while (cursor.moveToNext()) {
            val identificador = cursor.obterTexto(IDENTIFICADOR)
            val nome = cursor.obterTexto(NOME)
            val telefone = resolver.buscarTelefone(identificador)
            val contatoEncontrado = Contato(
                id = identificador,
                nome = nome,
                telefone = telefone,
                foto = R.drawable.icone_pessoa
            )
            contatos.add(contatoEncontrado)
        }

        cursor.close()

        return contatos
    }

    override fun adicionarContato(nome: String, telefone: String) {
        TODO("Not yet implemented")
    }

    private fun ContentResolver.buscarPorContatos(): Cursor? {
        return query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
    }

    private fun Cursor.obterTexto(identificador: String): String {
        return getString(getColumnIndex(identificador)).orEmpty()
    }

    private fun ContentResolver.buscarTelefone(identificador: String): String {
        val cursor = query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
            arrayOf(identificador),
            null
        )

        val telefone = cursor
            ?.takeIf { it.count > 0 }
            ?.takeIf { it.moveToNext() }
            ?.run { obterTexto(NUMERO_TELEFONE) }
            ?: ""

        cursor?.close()

        return telefone
    }

    companion object {
        private const val IDENTIFICADOR = ContactsContract.Contacts._ID
        private const val NOME = ContactsContract.Contacts.DISPLAY_NAME
        private const val NUMERO_TELEFONE = ContactsContract.CommonDataKinds.Phone.NUMBER
    }
}