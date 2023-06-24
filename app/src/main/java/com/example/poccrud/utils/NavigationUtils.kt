package com.example.poccrud.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable

class NavigationUtils {

    companion object {
        inline fun <reified T : Activity> Context.goToActivity(
            vararg params: Pair<String, Any?>, isFinished: Boolean = false
        ) {
            val intent = Intent(this, T::class.java)

            params.forEach { (key, value) ->
                when (value) {
                    is Parcelable -> intent.putExtra(key, value)
                    is String -> intent.putExtra(key, value)
                    //Adicionar conforme necessidade
                }
            }

            if (isFinished) intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
        }
    }
}
