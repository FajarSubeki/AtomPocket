package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import atompocket.id.R
import atompocket.id.util.AppConstant

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        transparentStatusBar()
        Handler().postDelayed({toActivity()}, AppConstant.LENGTH_SPLASH)

    }

    private fun toActivity(){
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

}