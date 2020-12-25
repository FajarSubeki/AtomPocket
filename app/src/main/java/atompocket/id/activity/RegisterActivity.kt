package atompocket.id.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import atompocket.id.R
import atompocket.id.databinding.ActivityRegisterBinding
import atompocket.id.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private lateinit var userViewModel: AuthViewModel
    var isCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        userViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authViewModel = userViewModel

        transparentStatusBar()

        initView()
    }

    private fun initView(){

        btnCreateAccount.setOnClickListener(this)
        tvAlreadyAccount.setOnClickListener(this)
        btnCreateAccount.setOnClickListener(this)
        cbTNC.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {

        if (p0 == btnCreateAccount){
            userViewModel?.registerClick(isCheck, listener)
        }else if (p0 == tvAlreadyAccount){
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

    }

    private  val listener = object : AuthViewModel.onSuccessListener{
        override fun onSuccess() {
            Intent()
        }
    }

    private fun Intent(){
        startActivity(Intent(this, SuccessRegisterActivity::class.java))
        finish()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p1){
            isCheck = true
            cbTNC.background = getBackground(R.drawable.bg_checkbox_check)
        }else{
            isCheck = false
            cbTNC.background = getBackground(R.drawable.bg_checkbox)
        }
    }

}