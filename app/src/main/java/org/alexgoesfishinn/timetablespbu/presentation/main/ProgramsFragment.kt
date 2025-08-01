package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.ProgramsFragmentBinding

class ProgramsFragment: Fragment(R.layout.programs_fragment) {
    private  var binding: ProgramsFragmentBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramsFragmentBinding.bind(view)
        Log.i("Programs", "SUCCESS")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}