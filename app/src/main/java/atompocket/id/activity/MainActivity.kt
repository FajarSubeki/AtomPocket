package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentStatusBar()

        initView()
    }

    private fun initView(){
        ivQrCode.setOnClickListener {
            startActivity(Intent(this, QrCodeActivity::class.java))
        }
        llTransaction.setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }
        llWallet.setOnClickListener {
            startActivity(Intent(this, WalletActivity::class.java))
        }
    }
}