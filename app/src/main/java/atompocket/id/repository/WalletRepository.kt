package atompocket.id.repository

import android.app.Application
import androidx.lifecycle.LiveData
import atompocket.id.database.AtomDatabase
import atompocket.id.database.Transaction
import atompocket.id.database.Wallet
import atompocket.id.database.WalletDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WalletRepository(application: Application) {

    private val walletDao: WalletDao
    private val allWallets: LiveData<List<Wallet>>
    private val allWalletsWhereStatus: LiveData<List<Wallet>>
    private val allTransaction: LiveData<List<Transaction>>

    init {
        val database = AtomDatabase.getInstance(application.applicationContext)
        walletDao = database!!.walletDao()
        allTransaction = walletDao.getAllTransaction()
        allWallets = walletDao.getAllTodoList()
        allWalletsWhereStatus = walletDao.getWalletActive("1")
    }

    fun saveWallet(wallet: Wallet) = runBlocking {
        this.launch(Dispatchers.IO) {
            walletDao.saveWallet(wallet)
        }
    }

    fun deleteWallets(wallet: Wallet) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                walletDao.deleteWallet(wallet)
            }
        }
    }

    fun getWalletList(): LiveData<List<Wallet>> {
        return allWallets
    }

    fun getWalletActive(): LiveData<List<Wallet>> {
        return allWalletsWhereStatus
    }

    fun saveTransaction(transaction: Transaction) = runBlocking {
        this.launch(Dispatchers.IO) {
            walletDao.saveTransaction(transaction)
        }
    }

    fun getTransactionList(): LiveData<List<Transaction>> {
        return allTransaction
    }

}