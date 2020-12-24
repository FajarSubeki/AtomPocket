package atompocket.id.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R

class SuccessRegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_register)

        transparentStatusBar()
    }

}