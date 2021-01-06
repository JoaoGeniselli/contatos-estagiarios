package com.examplo.contatos.contatos.utilidades

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

typealias PermissoesCallback = () -> Unit

class GerenciadorDePermissoes(
    private val codigoDeRequisicaoDePermissoes: Int,
    private val permissoesParaChecar: List<String>,
    private val fluxoPermissoesConcedidas: PermissoesCallback,
    private val fluxoPermissoesNegadas: PermissoesCallback
) {

    fun executarFluxoDePermissoes(activity: Activity) {
        if (verificarPermissoes(activity, permissoesParaChecar)) {
            fluxoPermissoesConcedidas()
        } else {
            solicitarPermissoesAoUsuario(activity)
        }
    }

    private fun verificarPermissoes(context: Context, permissions: List<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun solicitarPermissoesAoUsuario(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            permissoesParaChecar.toTypedArray(),
            codigoDeRequisicaoDePermissoes
        )
    }

    fun validarPermissoesDepoisDeSolicitarAoUsuario(activity: Activity) {
        if (verificarPermissoes(activity, permissoesParaChecar)) {
            fluxoPermissoesConcedidas()
        } else {
            fluxoPermissoesNegadas()
        }
    }

}