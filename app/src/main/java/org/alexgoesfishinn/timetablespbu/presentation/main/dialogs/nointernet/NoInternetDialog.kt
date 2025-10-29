package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.nointernet

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class NoInternetDialog: DialogFragment() {
    @Inject
    lateinit var warningsNotificator: WarningsNotificator

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        warningsNotificator.internetIsNotAvailableNotifyOff()
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.no_internet))
                .setMessage(it.getString(R.string.check_internet_connection))
                .setPositiveButton(it.getString(R.string.ok)) { dialog, _ -> dialog.dismiss()}
                    .create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}