package org.alexgoesfishinn.timetablespbu.data.network.utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import org.alexgoesfishinn.timetablespbu.R
import javax.inject.Inject

/**
 * @author a.bylev
 */
class InternetChecker @Inject constructor(
    @ApplicationContext private val context: Context
)  {
    fun isInternetAvailable(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

     fun showNoInternetDialog(context: Context){
        val builder = AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.no_internet))
            .setMessage(context.getString(R.string.check_internet_connection))
        val noInternetDialog = builder.create()
        noInternetDialog.show()
    }

    fun somethingWentWrong(context: Context){
        val builder = AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.oops))
            .setMessage(R.string.something_went_wrong)
        val somethingWentWrongDialog = builder.create()
        somethingWentWrongDialog.show()
    }


}