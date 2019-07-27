package com.diegaspar.recipesbook.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.diegaspar.recipesbook.R
import com.diegaspar.recipesbook.extension.inTransaction

abstract class BaseActivity(private val backButton: Boolean) : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        addFragment(savedInstanceState)
        if (backButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                R.id.fragmentContainer, fragment()
            )
        }

    abstract fun fragment(): BaseFragment
}