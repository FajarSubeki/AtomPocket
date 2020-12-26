package atompocket.id.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_success_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class SuccessTransactionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_transaction)

        transparentStatusBar()
        initView()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initView(){
        val intent = intent
        if (intent != null){
            val type: String = intent.getStringExtra("type").toString()
            val saldo: String = intent.getStringExtra("saldo").toString()

            tvType.text = type
            tvValue.text = saldo

            val sdf = SimpleDateFormat("dd MMM yyyy")
            val currentDate = sdf.format(Date())
            tvDate2.text = currentDate
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

}