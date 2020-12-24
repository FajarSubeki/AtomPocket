package atompocket.id.activity

import android.graphics.drawable.Drawable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

open class BaseActivity : AppCompatActivity() {

    open fun transparentStatusBar(){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    open fun getBackground(drawable: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawable)
    }

}