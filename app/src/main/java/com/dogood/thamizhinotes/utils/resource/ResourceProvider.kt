package com.dogood.thamizhinotes.utils.resource

import android.content.Context
import android.preference.PreferenceManager
import androidx.annotation.NonNull
import com.dogood.thamizhinotes.utils.resource.BaseResourceProvider
import javax.inject.Inject

class ResourceProvider @Inject constructor(@NonNull val context: Context) : BaseResourceProvider {
    private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    override fun getString(id: Int): String {
        val preference = PreferenceManager.getDefaultSharedPreferences(context)
        val key =preference.getString(SELECTED_LANGUAGE,"en")
        var context1=context

        return "Process"
    }
}