package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.alexgoesfishinn.timetablespbu.R

class NoInternetDialog(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.no_internet))
                .setMessage(it.getString(R.string.check_internet_connection)).create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}