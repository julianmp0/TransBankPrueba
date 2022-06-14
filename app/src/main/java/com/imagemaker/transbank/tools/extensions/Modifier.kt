package com.imagemaker.transbank.tools.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

fun Modifier.visible(visibility: Boolean): Modifier {
    return if (visibility) {
        this.then(alpha(1f))
    } else {
        this.then(alpha(0f))
    }
}