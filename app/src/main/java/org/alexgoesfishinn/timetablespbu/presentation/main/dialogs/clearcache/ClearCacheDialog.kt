package org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.clearcache

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.NavGraphDirections
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.usecases.DeleteAllDataUseCase
import javax.inject.Inject
/**
 * @author a.bylev
 */
@AndroidEntryPoint
class ClearCacheDialog : DialogFragment() {
    @Inject lateinit var deleteAllDataUseCase: DeleteAllDataUseCase
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.clear_cache))
                .setMessage(it.getString(R.string.clear_cache_message))
                .setPositiveButton(it.getString(R.string.remove)) { _, _ ->
                    clearCache()
                }
                .setNegativeButton(it.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun clearCache() {
        Log.i("DeleteCache", "Delete")
        lifecycleScope.launch {
            deleteAllDataUseCase.deleteAllData()
            val action = NavGraphDirections.toMain()
            findNavController().navigate(action)
            dismiss()
        }
    }
}