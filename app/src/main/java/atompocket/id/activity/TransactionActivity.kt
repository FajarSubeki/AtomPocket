package atompocket.id.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import atompocket.id.R
import atompocket.id.database.Transaction
import atompocket.id.database.Wallet
import atompocket.id.util.AppConstant
import atompocket.id.util.PreferenceUtil
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_add_wallet.*
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.toolbar_view.*
import kotlinx.android.synthetic.main.toolbar_view.tvTitle

class TransactionActivity : BaseActivity() {

    private lateinit var walletViewModel: WalletViewModel
    private var type: String? = null
    private var wallet_type: String? = null
    private var wallet_id: Long? = null
    private var wallet_saldo: String? = null
    private var saldo: String? = null
    lateinit var wallet: Wallet

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
            val customDropDownAdapter = atompocket.id.adapter.SpinnerAdapter(this, it)
            tvText06.adapter = customDropDownAdapter
        })

        tvText06.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                wallet = adapterView.selectedItem as Wallet
                wallet_type = wallet.title
                wallet_id = wallet.id
                wallet_saldo = wallet.saldo
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        ivBack.setOnClickListener {
            finish()
        }

        btnSendTrans.setOnClickListener {
            addTransaction()
        }
    }

    private fun addTransaction(){
        if (validateFields()){

            saldo = tvTitleSuccess.text.toString()

            if (type.equals("Uang Masuk")){

                val updateSaldo: String = wallet_saldo?.toInt()?.plus(saldo!!.toInt()).toString()
                walletViewModel.udpateWalletSaldo(updateSaldo, wallet)

                PreferenceUtil.getEditor(getApplication())?.putInt(PreferenceUtil.UANG_MASUK, saldo!!.toInt())?.commit()
                val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.plus(saldo!!.toInt())
                if (saldoTotal != null) {
                    PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
                }

            }else if (type.equals("Uang Keluar")){

                val updateSaldo: String = wallet_saldo?.toInt()?.minus(saldo!!.toInt()).toString()
                walletViewModel.udpateWalletSaldo(updateSaldo, wallet)

                PreferenceUtil.getEditor(getApplication())?.putInt(PreferenceUtil.UANG_KELUAR, saldo!!.toInt())?.commit()
                val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.minus(saldo!!.toInt())
                if (saldoTotal != null) {
                    PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
                }
            }

            val transaction = Transaction(id = null, nominal = saldo!!, type = type.toString(), wallet_type = wallet_type.toString(), desc = tvText09.text.toString())
            walletViewModel.saveTransaction(transaction)
            val intent = Intent(applicationContext, SuccessTransactionActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("saldo", saldo)
            startActivity(intent)
            finish()
        }
    }

    private fun validateFields(): Boolean {
        if (tvTitleSuccess.text.isEmpty()) {
            tvTitleSuccess.error = getString(R.string.pleaseEnterSaldo)
            tvTitleSuccess.requestFocus()
            return false
        }
        if (tvTitleSuccess.text.toString().equals("0")) {
            tvTitleSuccess.error = getString(R.string.pleaseEnterSaldo)
            tvTitleSuccess.requestFocus()
            return false
        }
        if (wallet_saldo.toString().equals("0")) {
            tvTitleSuccess.error = getString(R.string.str_kurang)
            tvTitleSuccess.requestFocus()
            return false
        }
        return true
    }

}