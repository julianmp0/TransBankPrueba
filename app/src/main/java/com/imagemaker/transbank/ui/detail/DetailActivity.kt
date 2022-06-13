package com.imagemaker.transbank.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.Coil
import com.bumptech.glide.Glide
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

        fillViews(character?:return)

    }

    private fun fillViews(character: Result) {
        binding.apply {
            Glide.with(this@DetailActivity).load(character.image).placeholder(R.drawable.default_image).into(ivCharacter)
            tvNameCharacter.text = character.name
            tvOriginCharacter.text =  "Origen: ${character.origin?.name?:"Desconocido"}"
            tvLocationCharacter.text =  "Ubicación: ${character.location?.name?:"Desconocido"}"
            tvStatusCharacter.text = "Estado: ${character.status}"
            tvSpecieCharacter.text = "Especie: ${character.species}"
        }
    }
}