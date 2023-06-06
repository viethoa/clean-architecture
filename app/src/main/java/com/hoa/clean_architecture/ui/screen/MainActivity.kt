package com.hoa.clean_architecture.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hoa.clean_architecture.ui.base.BaseActivity
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.databinding.ActivityMainBinding
import com.hoa.clean_architecture.ui.base.DIParent
import javax.inject.Inject
import kotlin.reflect.KClass

class MainActivity : BaseActivity(), DIParent {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val component by lazy {
        DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getDependency(cls: KClass<T>): T {
        if (cls.isInstance(component)) {
            return component as T
        } else {
            throw Exception("Update MainComponent to extend from your ${cls.simpleName}")
        }
    }

    override fun setupDependencyInjection() {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        viewModel.uiState.observe(this) { state ->
            when (state) {
                MainUiState.GetMenuItemException -> {
                    Toast.makeText(this, "Get Item Detail Exceptions", Toast.LENGTH_SHORT).show()
                }
                MainUiState.FavoriteException -> {
                    Toast.makeText(this, "Favorite Exception", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getMenuItem()
    }
}