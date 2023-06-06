package com.hoa.clean_architecture.ui.view.closeicon

import androidx.lifecycle.LiveData
import com.hoa.clean_architecture.R

data class CloseIcon(
    val type: CloseIconType = CloseIconType.CIRCLE_CLOSE,
    val tintColor: Int = R.color.white
)

enum class CloseIconType {
    /**
     * As single "X" icon only [R.drawable.ic_close_white]
     */
    CLOSE,

    /**
     * Have circle Background and "X" icon [R.drawable.ic_close_circle_white]
     */
    CIRCLE_CLOSE
}

interface ICloseIcon {
    /**
     * To config how does icon looks like
     */
    val data: LiveData<CloseIcon>
}