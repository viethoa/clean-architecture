package com.hoa.clean_architecture.ui.screen.body

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R

class BodyViewModel constructor(
    private val rootView: View,
    private val lifecycleOwner: LifecycleOwner
) {

    private val name: TextView by lazy { rootView.findViewById(R.id.body_title) }
    private val description: TextView by lazy { rootView.findViewById(R.id.body_description) }

    fun initializeUI(handler: IBodyView) {
        handler.data.observe(lifecycleOwner) {
            name.text = it.name.orEmpty()
            description.text = it.description.orEmpty()
        }
    }
}