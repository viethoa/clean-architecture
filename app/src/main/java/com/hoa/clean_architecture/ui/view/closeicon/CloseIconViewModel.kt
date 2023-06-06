package com.hoa.clean_architecture.ui.view.closeicon

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.component.Navigator
import com.hoa.clean_architecture.component.ResourcesProvider

class CloseIconViewModel constructor(
    private val rootView: View,
    private val navigator: Navigator,
    private val lifecycleOwner: LifecycleOwner,
    private val resourcesProvider: ResourcesProvider
) {

    private val closeButton: ImageView by lazy { rootView.findViewById(R.id.view_close_button) }

    fun initializeUI(handler: ICloseIcon) {
        with(handler) {
            data.observe(lifecycleOwner) { data ->
                // Icon resource
                closeButton.setImageResource(
                    when (data.type) {
                        CloseIconType.CLOSE -> R.drawable.ic_close_white
                        CloseIconType.CIRCLE_CLOSE -> R.drawable.ic_close_circle_white
                    }
                )
                // Tint color
                closeButton.setColorFilter(
                    resourcesProvider.getColor(data.tintColor)
                )
            }
            closeButton.setOnClickListener {
                navigator.exitScreen()
            }
        }
    }
}