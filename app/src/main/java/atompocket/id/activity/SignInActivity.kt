package atompocket.id.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import atompocket.id.activity.MainActivity
import atompocket.id.R
import atompocket.id.databinding.ActivityRegisterBinding
import atompocket.id.databinding.ActivitySignInBinding
import atompocket.id.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity(), View.OnClickListener {

    private lateinit var userViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        userViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authViewModel = userViewModel

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        tvForgotPassword.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)
        tvCreateAccount.setOnClickListener(this)
    }

    private  val listener = object : AuthViewModel.onSuccessListener{
        override fun onSuccess() {
            Intent()
        }
    }

    private fun Intent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onClick(p0: View?) {
        if (p0 == tvForgotPassword){
            showToast("Coming Soon Features...")
        }else if (p0 == btnSignIn){
            userViewModel.loginClick(listener)
        }else if (p0 == tvCreateAccount){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}