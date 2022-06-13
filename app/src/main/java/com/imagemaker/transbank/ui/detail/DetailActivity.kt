package com.imagemaker.transbank.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imagemaker.transbank.R
import com.imagemaker.transbank.databinding.ActivityDetailBinding
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.tools.Constans

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val character: Result? = intent.getSerializableExtra(Constans.EXTRAS.CHARACTER) as? Result
        character
    }
}