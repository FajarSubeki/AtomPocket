package atompocket.id.activity

import android.os.Bundle
import atompocket.id.R
import kotlinx.android.synthetic.main.activity_qr_code.*

class QrCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        transparentStatusBar()

        // set bacgkround
        ivAvatar2.setImageDrawable(getBackground(R.drawable.avatar))
        ivBarcode.setImageDrawable(getBackground(R.drawable.ic_barcode))
        ivQrcode.setImageDrawable(getBackground(R.drawable.qrcode2))
        ivLogo.setImageDrawable(getBackground(R.drawable.icon_blue))
        ivIde.setImageDrawable(getBackground(R.drawable.idea))
    }

}