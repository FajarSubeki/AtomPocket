package atompocket.id.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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

    private fun initView(){

        // set background
        ivAvatar.background = getBackground(R.drawable.avatar)
        ivQrCode.background = getBackground(R.drawable.qr_code)
        ivTrans.background = getBackground(R.drawable.send)
        ivWallet.background = getBackground(R.drawable.credit_card)
        ivReport.background = getBackground(R.drawable.grafik)

        // init onclick event
        ivQrCode.setOnClickListener(this)
        llTransaction.setOnClickListener(this)
        llWallet.setOnClickListener(this)
        llReport.setOnClickListener(this)
        tvDetailDesc.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        // set default saldo 2.500.000
        val saldoValue: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.SALDO, 0)
        if (saldoValue == 0){
            PreferenceUtil.getEditor(getApplication())?.putInt(PreferenceUtil.SALDO, AppConstant.SALDO_MASUK)?.commit()
        }

        val formatter: NumberFormat = DecimalFormat("#,###")

        // set uang masuk and uang keluar from transaction
        val uangMasuk: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.UANG_MASUK, 0)
        val uangKeluar: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.UANG_KELUAR, 0)
        tvSumIncome.text = "Rp. " + formatter.format(uangMasuk)
        tvSumOutcome.text = "Rp. " + formatter.format(uangKeluar)

        // set description with name ser login
        if (SessionManager.getProfile(getApplication()) == null) return
        val own: User? = SessionManager.getProfile(getApplication())
        if (own != null) {
            tvDescPengeluaran.setText("Hai " + own.fullname + getString(R.string.str_desc_main))
        }

        // set format number for current saldo
        tvTitleSuccess.text = "Rp. " +  formatter.format(saldoValue)
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
        }else if (p0 == tvDetailDesc){
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }
}