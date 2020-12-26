package atompocket.id.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import atompocket.id.R
import atompocket.id.adapter.WalletAdapter
import atompocket.id.database.Wallet
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.toolbar_view.*

class WalletActivity : BaseActivity(), WalletAdapter.walletEvents {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        transparentStatusBar()
        initView()
    }

    private fun initView(){

        //Setting up RecyclerView
        rvWallet.layoutManager = LinearLayoutManager(this)
        walletAdapter = WalletAdapter(this)
        rvWallet.adapter = walletAdapter

        //Setting up ViewModel and LiveData
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        walletViewModel.getAllTodoList().observe(this, Observer {
            walletAdapter.setAllTodoItems(it)
        })

        ivBack.setOnClickListener {
            finish()
        }

        tvTitleWallet.setOnClickListener{
            startActivity(Intent(this, AddWalletActivity::class.java))
        }
    }

    override fun onMenuClicked(wallet: Wallet) {
        walletViewModel.deleteTodo(wallet)
    }

    override fun onSwitchClicked(wallet: Wallet, isCheck: Boolean) {
        if (isCheck){
            walletViewModel.udpateWalletStatus(1, wallet)
        }else{
            walletViewModel.udpateWalletStatus(0, wallet)
        }
    }

}