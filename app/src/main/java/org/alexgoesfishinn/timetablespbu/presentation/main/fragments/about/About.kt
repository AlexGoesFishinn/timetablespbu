package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.about

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.alexgoesfishinn.timetablespbu.BuildConfig
import org.alexgoesfishinn.timetablespbu.R


class About: Fragment(R.layout.about_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val version: String = BuildConfig.VERSION_NAME
        val versionString: TextView = view.findViewById(R.id.about_fragment_version)
        versionString.text = getString(R.string.application_version, version)
    }
}