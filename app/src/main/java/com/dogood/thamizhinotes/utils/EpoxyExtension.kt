package com.dogood.thamizhinotes.utils

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView

/** Easily add models to an EpoxyRecyclerView, the same way you would in a buildModels method of SampleController. */
fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
    setControllerAndBuildModels(object : EpoxyController() {
        override fun buildModels() {
            try {
                buildModelsCallback()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    })
}

