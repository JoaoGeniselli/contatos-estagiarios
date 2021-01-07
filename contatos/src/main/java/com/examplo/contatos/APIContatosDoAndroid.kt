package com.examplo.contatos

import android.content.ContentProviderOperation
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

    //Precisa da permissão: Manifest.permission.WRITE_CONTACTS para funcionar
    override fun adicionarContato(nome: String, telefone: String) {
        val resolver = applicationContext?.contentResolver ?: return
        val informacoesSobreConta =
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()

        val informacoesSobreNome =
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Contacts.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, nome)
                .build()

        val informacoesSobreNumero =
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telefone)
                .withValue(
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_HOME
                )
                .build()

        val todasInfomacoesDoContato =
            arrayListOf(informacoesSobreConta, informacoesSobreNome, informacoesSobreNumero)

        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, todasInfomacoesDoContato)
        } catch (e: Exception) {
            e.printStackTrace()
        }
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