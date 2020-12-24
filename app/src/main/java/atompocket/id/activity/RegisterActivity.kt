package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, SuccessRegisterActivity::class.java))
            finish()
        }

        tvAlreadyAccount.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }

}