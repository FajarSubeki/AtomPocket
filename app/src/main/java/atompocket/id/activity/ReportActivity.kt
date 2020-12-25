package atompocket.id.activity

import android.annotation.SuppressLint
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.toolbar_view.*

class ReportActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        transparentStatusBar()

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        tvTitle.text = "Laporan"

        ivBack.setOnClickListener {
            finish()
        }

    }

}