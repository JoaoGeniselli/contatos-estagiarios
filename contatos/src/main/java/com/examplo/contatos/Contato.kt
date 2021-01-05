package com.examplo.contatos

import androidx.annotation.DrawableRes
import java.io.Serializable

const val CONTATO_EXTRA_ID = "CONTATO_EXTRA_ID"

data class Contato(
    val id: String,
    val nome: String,
    val telefone: String,
    @DrawableRes val foto: Int
) : Serializable