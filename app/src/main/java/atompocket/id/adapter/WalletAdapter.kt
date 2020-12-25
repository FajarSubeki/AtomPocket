package atompocket.id.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import atompocket.id.R
import atompocket.id.database.Wallet
import kotlinx.android.synthetic.main.item_wallet.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class WalletAdapter(walletEvents: walletEvents) : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    private var todoList: List<Wallet> = arrayListOf()
    private var filteredTodoList: List<Wallet> = arrayListOf()
    private val listener: walletEvents = walletEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTodoList[position], listener)

    }

    override fun getItemCount(): Int = filteredTodoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(wallet: Wallet, listener: walletEvents) {
            itemView.tvTitleWallet.text = wallet.title
            itemView.tvDescWallet.text = wallet.desc

            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = wallet.saldo.toInt()
            itemView.tvSumWallet.text = "Rp. " + formatter.format(saldoValue)

            itemView.ivMenu.setOnClickListener {
                listener.onMenuClicked(wallet)
            }

//            itemView.swWallet.setOnCheckedChangeListener() {
//                listener.onSwitchClicked()
//            }
        }

    }

    fun setAllTodoItems(todoItems: List<Wallet>) {
        this.todoList = todoItems
        this.filteredTodoList = todoItems
        notifyDataSetChanged()
    }

    interface walletEvents{
        fun onMenuClicked(wallet: Wallet)
        fun onSwitchClicked()
    }

}