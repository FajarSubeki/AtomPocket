package atompocket.id.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.toolbar_view.*

class WalletActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        transparentStatusBar()
        initView()
    }

    private fun initView(){

        ivBack.setOnClickListener {
            finish()
        }

    }

}