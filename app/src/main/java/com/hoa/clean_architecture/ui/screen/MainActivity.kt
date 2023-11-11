package com.hoa.clean_architecture.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hoa.clean_architecture.ui.base.BaseActivity
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val component by lazy {
        DaggerMainComponent.builder()
            .mainModule(MainModule(this, this))
            .build()
    }

    override fun getActivityComponent(): Any = component

    override fun setupDependencyInjection() {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is MainUiState.GetMenuItemError -> {
                    Toast.makeText(this, "Get Item Detail Exceptions", Toast.LENGTH_SHORT).show()
                }
                is MainUiState.FavoriteError -> {
                    Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getMenuItem()
    }
}