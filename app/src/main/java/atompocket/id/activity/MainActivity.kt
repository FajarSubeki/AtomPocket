package atompocket.id.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import atompocket.id.R
import atompocket.id.pojo.User
import atompocket.id.util.AppConstant
import atompocket.id.util.PreferenceUtil
import atompocket.id.util.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentStatusBar()

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        PreferenceUtil.getEditor(getApplication())?.putInt(PreferenceUtil.SALDO, AppConstant.SALDO_MASUK)?.commit()

        if (SessionManager.getProfile(getApplication()) == null) return
        val own: User? = SessionManager.getProfile(getApplication())
        Log.e("OWN", own.toString())

        val saldoValue: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.SALDO, 0)
        val formatter: NumberFormat = DecimalFormat("#,###")
        tvTitleSuccess.text = "Rp. " +  formatter.format(saldoValue)

        ivQrCode.setOnClickListener(this)
        llTransaction.setOnClickListener(this)
        llWallet.setOnClickListener(this)
        llReport.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 == ivQrCode){
            startActivity(Intent(this, QrCodeActivity::class.java))
        }else if (p0 == llTransaction){
            startActivity(Intent(this, TransactionActivity::class.java))
        }else if (p0 == llWallet){
            startActivity(Intent(this, WalletActivity::class.java))
        }else if (p0 == llReport){
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }
}