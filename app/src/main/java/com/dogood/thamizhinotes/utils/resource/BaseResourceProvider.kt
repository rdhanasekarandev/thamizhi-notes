package com.dogood.thamizhinotes.utils.resource

import androidx.annotation.NonNull
import androidx.annotation.StringRes

interface BaseResourceProvider{
    /**
     * Resolves text's id to String.
     *
     * @param id to be fetched from the resources
     * @return String representation of the {@param id}
     */
    @NonNull
    fun getString(@StringRes id: Int): String
}