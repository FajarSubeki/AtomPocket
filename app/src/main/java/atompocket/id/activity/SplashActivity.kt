package atompocket.id.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import atompocket.id.R
import atompocket.id.util.AppConstant
import atompocket.id.util.PreferenceUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        transparentStatusBar()
        imgLogo.setImageDrawable(getBackground(R.drawable.atomic_logo))
        Handler().postDelayed({ toActivity() }, AppConstant.LENGTH_SPLASH)

    }

    private fun toActivity(){

        val isRegisterDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_REGISTER, false)
        val isLoginDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_LOGIN, false)

        if (isRegisterDone == true && isLoginDone == false){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else if (isRegisterDone == false && isLoginDone == true){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else if (isRegisterDone == true && isLoginDone == true){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }

}