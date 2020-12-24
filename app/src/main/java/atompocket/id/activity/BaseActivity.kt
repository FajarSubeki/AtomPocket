package atompocket.id.activity

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    open fun transparentStatusBar(){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

}