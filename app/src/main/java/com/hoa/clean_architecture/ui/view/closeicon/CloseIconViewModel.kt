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
    private val resourcesProvider: ResourcesProvider
) : View.OnClickListener {

    private val closeButton by lazy { rootView.findViewById<ImageView>(R.id.view_close_button) }

    fun initializeUI(handler: ICloseIcon, lifecycleOwner: LifecycleOwner) {
        with(handler) {
            closeIcon.observe(lifecycleOwner) { data ->
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
            closeButton.setOnClickListener(this@CloseIconViewModel)
        }
    }

    override fun onClick(v: View?) {
        navigator.exitScreen()
    }
}