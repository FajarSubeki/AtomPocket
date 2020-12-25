package atompocket.id.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import atompocket.id.R
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.toolbar_view.*
import kotlinx.android.synthetic.main.toolbar_view.tvTitle

class TransactionActivity : BaseActivity() {

    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        transparentStatusBar()

        //Setting up ViewModel and LiveData
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        tvTitle.text = "Transaksi Baru"

        val values : Array<String> = arrayOf("Uang Masuk", "Uang Keluar")
        tvText02.adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            values
        )

        tvText02.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                adapterView.selectedItem
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        //Get wallet active

        walletViewModel.getWalletActive().observe(this, Observer {
            for(item in it){

                val values : Array<String> = arrayOf(item.saldo + " " + item.saldo)
                tvText06.adapter = ArrayAdapter<String>(
                    applicationContext,
                    android.R.layout.simple_list_item_1, values
                )
            }
        })

        tvText06.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                adapterView.selectedItem
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })



        ivBack.setOnClickListener {
            finish()
        }

        btnSendTrans.setOnClickListener {
            startActivity(Intent(this, SuccessTransactionActivity::class.java))
        }
    }

}