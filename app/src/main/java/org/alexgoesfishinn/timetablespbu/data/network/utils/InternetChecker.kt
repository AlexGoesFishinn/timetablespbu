package org.alexgoesfishinn.timetablespbu.data.network.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * @author a.bylev
 */
class InternetChecker @Inject constructor(
private val context: Context
)  {
    private val _isInternetAvailableFlag: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isInternetAvailableFlag: StateFlow<Boolean> = _isInternetAvailableFlag.asStateFlow()
    private val _apiError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val apiError: StateFlow<Boolean> = _apiError.asStateFlow()

    fun isInternetAvailable(): Boolean{
        _isInternetAvailableFlag.value = checkInternet()
        return checkInternet()
    }

    private fun checkInternet(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    fun apiErrorOccurs(){
        _apiError.value = true
        _apiError.value = false
    }



}