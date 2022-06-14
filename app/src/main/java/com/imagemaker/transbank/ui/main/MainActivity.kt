package com.imagemaker.transbank.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.imagemaker.transbank.tools.Constans
import com.imagemaker.transbank.ui.detail.DetailActivity
import com.imagemaker.transbank.ui.theme.TransBankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransBankTheme {
                MainScreen {character ->
                    startActivity(
                        Intent(
                            this, DetailActivity::class.java
                        ).apply {
                            putExtra(Constans.EXTRAS.CHARACTER, character)
                        }
                    )
                }
            }
        }
    }
}