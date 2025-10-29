package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.alexgoesfishinn.timetablespbu.R
/**
 * @author a.bylev
 */
class MoreDetailsDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?. let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.group_is_not_available))
                .setMessage(it.getString(R.string.group_is_not_available_details))
                .setPositiveButton(it.getString(R.string.ok)) {dialog, _ -> dialog.dismiss()}
                .create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}