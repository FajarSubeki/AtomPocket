package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import atompocket.id.R
import atompocket.id.pojo.User
import atompocket.id.util.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        if (SessionManager.getProfile(getApplication()) == null) return
        val own: User? = SessionManager.getProfile(getApplication())
        Log.e("OWN", own.toString())

        ivQrCode.setOnClickListener {
            startActivity(Intent(this, QrCodeActivity::class.java))
        }
        llTransaction.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }
        llWallet.setOnClickListener {
            startActivity(Intent(this, WalletActivity::class.java))
        }
        llReport.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }
}