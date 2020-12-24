package atompocket.id.activity

import android.content.Intent
import android.os.Bundle
import atompocket.id.activity.MainActivity
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        tvForgotPassword.setOnClickListener {
            showToast("Coming Soon Features...")
        }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}