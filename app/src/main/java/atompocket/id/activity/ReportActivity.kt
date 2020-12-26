package atompocket.id.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import atompocket.id.R
import atompocket.id.adapter.TransactionAdapter
import atompocket.id.adapter.WalletAdapter
import atompocket.id.database.Transaction
import atompocket.id.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.toolbar_view.*

class ReportActivity : BaseActivity(), TransactionAdapter.transactionEvent {

    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        transparentStatusBar()

        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView(){

        tvTitle.text = "Laporan"

        //Setting up RecyclerView
        rvTransaction.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter(this)
        rvTransaction.adapter = transactionAdapter

        //Setting up ViewModel and LiveData
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        walletViewModel.getAllTransaction().observe(this, Observer {
            transactionAdapter.setAllTransItems(it)
        })

        ivBack.setOnClickListener {
            finish()
        }

    }

    override fun onDeleteClicked(transaction: Transaction) {
        walletViewModel.deleteTrans(transaction)
    }

}