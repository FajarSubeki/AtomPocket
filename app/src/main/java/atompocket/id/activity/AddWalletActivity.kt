package atompocket.id.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import atompocket.id.R
import atompocket.id.database.Wallet
import atompocket.id.util.PreferenceUtil
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_add_wallet.*

class AddWalletActivity : BaseActivity() {

    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)

        transparentStatusBar()

        btnCreate.setOnClickListener {
            saveWallet()
        }
    }

    private fun saveWallet(){
        if (validateFields()){
            val getSaldo: String = etSaldo.text.toString()
            val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.plus(getSaldo.toInt())
            if (saldoTotal != null) {
                PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
            }
            val wallet = Wallet(id = null, title = etTitle.text.toString(), desc = etDesc.text.toString(), saldo = getSaldo, status = 1)
            walletViewModel.saveTodo(wallet)
            finish()
        }
    }

    /**
     * Validation of EditText
     * */
    private fun validateFields(): Boolean {
        if (etTitle.text.isEmpty()) {
            etTitle.error = getString(R.string.pleaseEnterTitle)
            etTitle.requestFocus()
            return false
        }
        if (etDesc.text.isEmpty()) {
            etDesc.error = getString(R.string.pleaseEnterDesc)
            etDesc.requestFocus()
            return false
        }
        if (etSaldo.text.isEmpty()) {
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        if (etSaldo.text.toString().equals("0")){
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        return true
    }

}