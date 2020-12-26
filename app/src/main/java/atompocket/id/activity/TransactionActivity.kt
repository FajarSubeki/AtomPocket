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
import atompocket.id.database.Transaction
import atompocket.id.database.Wallet
import atompocket.id.util.AppConstant
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_add_wallet.*
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.toolbar_view.*
import kotlinx.android.synthetic.main.toolbar_view.tvTitle

class TransactionActivity : BaseActivity() {

    private lateinit var walletViewModel: WalletViewModel
    private var type: String? = null
    private var wallet_type: String? = null

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
                type = adapterView.selectedItem as String?
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        //Get wallet active

        walletViewModel.getWalletActive().observe(this, Observer {
            for(item in it){
                val values = arrayOf(item.title + " " + item.saldo)
                wallet_type = item.title
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
            addTransaction()
            val intent = Intent(applicationContext, SuccessTransactionActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("saldo", tvTitleSuccess.text.toString())
            startActivity(intent)
            finish()
        }
    }

    private fun addTransaction(){
        if (validateFields()){
            val transaction = Transaction(id = null, nominal = tvTitleSuccess.text.toString(), type = type.toString(), wallet_type = wallet_type.toString(), desc = tvText09.text.toString())
            walletViewModel.saveTransaction(transaction)

        }
    }

    private fun validateFields(): Boolean {
        if (tvTitleSuccess.text.isEmpty()) {
            tvTitleSuccess.error = getString(R.string.pleaseEnterTitle)
            tvTitleSuccess.requestFocus()
            return false
        }
        return true
    }

}