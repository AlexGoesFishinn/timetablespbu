package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.about

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.alexgoesfishinn.timetablespbu.BuildConfig
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.utils.Animations
import javax.inject.Inject


class About: Fragment(R.layout.about_fragment) {
    @Inject
    lateinit var animations: Animations
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val versionString: TextView = view.findViewById(R.id.about_fragment_version)
        val changedEventDescription: TextView = view.findViewById(R.id.about_fragment_changed)
        val cancelledEventDescription: TextView = view.findViewById(R.id.about_fragment_cancelled)
        val eventWithManyLocationsDescription: TextView = view.findViewById(R.id.about_fragment_locations)
        val layout: ConstraintLayout = view.findViewById(R.id.about_fragment_layout)
        animations.fadeIn(layout)
        initAppVersionString(versionString)
        initChangedEventDescription(changedEventDescription)
        initCancelledEventDescription(cancelledEventDescription)
        initEventWithManyLocationsDescription(eventWithManyLocationsDescription)

    }

    private fun initAppVersionString(view: TextView){
        val version: String = BuildConfig.VERSION_NAME
        view.text = getString(R.string.application_version, version)
    }

    private fun initChangedEventDescription(view: TextView){
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.spbu_event_changed_color)
        val backgroundColorSpan = BackgroundColorSpan(backgroundColor)
        val spannedString = SpannableString(getString(R.string.about_fragment_changed_text_start))
        spannedString.setSpan(backgroundColorSpan, 0, spannedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val description: String = getString(R.string.about_fragment_changed_text_end)
        val builder = SpannableStringBuilder()
        builder.append(spannedString).append(description)
        view.setText(builder, TextView.BufferType.SPANNABLE)
    }

    private fun initCancelledEventDescription(view: TextView){
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.spbu_event_changed_color)
        val backgroundColorSpan = BackgroundColorSpan(backgroundColor)
        val spannedString = SpannableString(getString(R.string.about_fragment_cancelled_text_start))
        spannedString.setSpan(backgroundColorSpan, 0, spannedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannedString.setSpan(StrikethroughSpan(),0, spannedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val description: String = getString(R.string.about_fragment_cancelled_text_end)
        val builder = SpannableStringBuilder()
        builder.append(spannedString).append(description)
        view.setText(builder, TextView.BufferType.SPANNABLE)
    }

    private fun initEventWithManyLocationsDescription(view: TextView){
        val foregroundColor = ContextCompat.getColor(requireContext(),R.color.spbu_primary_terracot_color)
        val foregroundColorSpan = ForegroundColorSpan(foregroundColor)
        val spannedString = SpannableString(getString(R.string.about_fragment_locations_text_start))
        spannedString.setSpan(foregroundColorSpan, 0, spannedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannedString.setSpan(UnderlineSpan(),0, spannedString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val description = getString(R.string.about_fragment_locations_text_end)
        val builder = SpannableStringBuilder()
        builder.append(spannedString).append(description)
        view.setText(builder, TextView.BufferType.SPANNABLE)
    }
}