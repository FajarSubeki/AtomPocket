package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_success_register.*

class SuccessRegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_register)

        transparentStatusBar()

        ivThumbsUp.setImageDrawable(getBackground(R.drawable.thumbs_up))
        btnToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}