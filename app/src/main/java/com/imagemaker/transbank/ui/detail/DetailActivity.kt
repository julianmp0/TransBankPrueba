package com.imagemaker.transbank.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.imagemaker.transbank.R
import com.imagemaker.transbank.databinding.ActivityDetailBinding
import com.imagemaker.transbank.model.models.Result
import com.imagemaker.transbank.tools.Constans
import io.mpos.accessories.AccessoryFamily
import io.mpos.accessories.parameters.AccessoryParameters
import io.mpos.paybutton.MposUi
import io.mpos.paybutton.UiConfiguration
import io.mpos.provider.ProviderMode
import io.mpos.transactions.Currency
import io.mpos.transactions.parameters.TransactionParameters

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val character: Result? = intent.getSerializableExtra(Constans.EXTRAS.CHARACTER) as? Result

        fillViews(character?:return)





        /*

        val transactionParameters = TransactionParameters.Builder()
            .charge(1.toBigDecimal(), Currency.EUR)
            .build()

        val sss = create(this)

        val paybutton2 = create(
            activity,
            configuration.merchant.providerMode,
            configuration.merchant.identifier,
            configuration.merchant.secret
        )
        *
         */
    }

    private fun fillViews(character: Result) {
        binding.apply {
            Glide.with(this@DetailActivity).load(character.image).placeholder(R.drawable.default_image).into(ivCharacter)
            tvNameCharacter.text = character.name
            tvOriginCharacter.text =  "Origen: ${character.origin?.name?:"Desconocido"}"
            tvLocationCharacter.text =  "Ubicación: ${character.location?.name?:"Desconocido"}"
            tvStatusCharacter.text = "Estado: ${character.status}"
            tvSpecieCharacter.text = "Especie: ${character.species}"
            btnPrint.setOnClickListener { printReceipt() }
        }
    }

    private fun printReceipt() {
        val mposUi = MposUi.create(
            context = this,
            providerMode = ProviderMode.MOCK,
            merchantId = "1231242514",
            merchantSecret = "yourMerchantSecret"
        )

        val configuration = UiConfiguration(
            // For transacting with PAX terminals:
            automaticPrintingOption = UiConfiguration.AutomaticPrintingOption.MERCHANT_RECEIPT,
            terminalParameters = AccessoryParameters.Builder(AccessoryFamily.PAX)
                .integrated()
                .build()

        )
        mposUi.configuration = configuration

        val transactionParameters = TransactionParameters.Builder()
            .charge(1.toBigDecimal(), Currency.EUR)
            .build()

        val transactionIntent = mposUi.createTransactionIntent(transactionParameters)

        startActivityForResult(transactionIntent, MposUi.REQUEST_CODE_PAYMENT)

    }
}