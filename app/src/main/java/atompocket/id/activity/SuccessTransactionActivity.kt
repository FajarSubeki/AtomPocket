package atompocket.id.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_success_transaction.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class SuccessTransactionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_transaction)

        transparentStatusBar()
        initView()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initView(){
        val intent = intent
        if (intent != null){
            val type: String = intent.getStringExtra("type").toString()
            val saldo: String = intent.getStringExtra("saldo").toString()

            if (type.equals("Uang Keluar")){
                tvType.setTextColor(getColor(R.color.red_bg))
                tvValue.setTextColor(getColor(R.color.red_bg))
            }else{
                tvType.setTextColor(getColor(R.color.color_black_text))
                tvValue.setTextColor(getColor(R.color.color_black_text))
            }
            tvType.text = type

            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = saldo.toInt()
            tvValue.text = "Rp. " + formatter.format(saldoValue)

            val sdf = SimpleDateFormat("dd MMM yyyy")
            val currentDate = sdf.format(Date())
            tvDate2.text = currentDate
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

}