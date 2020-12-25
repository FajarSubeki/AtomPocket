package atompocket.id.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.toolbar_view.*

class TransactionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        transparentStatusBar()

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){
        tvTitle.text = "Transaksi Baru"

        ivBack.setOnClickListener {
            finish()
        }
    }

}