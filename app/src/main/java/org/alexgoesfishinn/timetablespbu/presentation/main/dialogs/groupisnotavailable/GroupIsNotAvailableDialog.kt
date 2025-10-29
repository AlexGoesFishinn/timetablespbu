package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable

import android.app.AlertDialog
import android.app.Dialog
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
class GroupIsNotAvailableDialog: DialogFragment() {
    @Inject
    lateinit var warningsNotificator: WarningsNotificator

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        warningsNotificator.groupIsNotAvailableNotifyOff()
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.group_is_not_available))
                .setMessage(it.getString(R.string.remove_from_favourite))
                .setPositiveButton(it.getString(R.string.ok)) { dialog, _ -> dialog.dismiss()}
                .create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}