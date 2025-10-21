package org.alexgoesfishinn.timetablespbu.data.network.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

/**
 * @author a.bylev
 */
class InternetChecker @Inject constructor(
private val context: Context
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

//     fun showNoInternetDialog(
////         context: Context
//     ){
//        val builder = AlertDialog.Builder(MainActivity())
//            .setTitle(context.getString(R.string.no_internet))
//            .setMessage(context.getString(R.string.check_internet_connection))
//        val noInternetDialog = builder.create()
//        noInternetDialog.show()
//    }

//    fun somethingWentWrong(
////        context: Context
//    ){
//        val builder = AlertDialog.Builder(MainActivity())
//            .setTitle(context.getString(R.string.oops))
//            .setMessage(R.string.something_went_wrong)
//        val somethingWentWrongDialog = builder.create()
//        somethingWentWrongDialog.show()
//    }


}