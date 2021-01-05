package com.examplo.contatos

import androidx.annotation.DrawableRes

data class Contato(
    val id: String,
    val nome: String,
    val telefone: String,
    @DrawableRes val foto: Int
)