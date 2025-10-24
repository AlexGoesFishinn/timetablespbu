package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.alexgoesfishinn.timetablespbu.R

class GroupIsNotAvailableDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.group_is_not_available))
                .setMessage(it.getString(R.string.remove_from_favourite))
                .setPositiveButton(it.getString(R.string.ok)) {dialog, _ -> dialog.dismiss()}
                .create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}