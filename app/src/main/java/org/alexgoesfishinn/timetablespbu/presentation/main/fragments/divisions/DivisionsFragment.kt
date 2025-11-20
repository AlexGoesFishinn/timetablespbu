package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.divisions

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.DivisionsFragmentBinding
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.DivisionsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.DivisionsClickListener
import org.alexgoesfishinn.timetablespbu.presentation.main.utils.Animations
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class DivisionsFragment : Fragment(R.layout.divisions_fragment) {
    private val viewModel by viewModels<DivisionsViewModel>()
    private var binding: DivisionsFragmentBinding? = null
    private lateinit var divisionRecycler: RecyclerView
    private lateinit var divisionAdapter: DivisionsAdapter
    private lateinit var layout: ConstraintLayout
    @Inject
    lateinit var animations: Animations


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DivisionsFragmentBinding.bind(view)
        divisionRecycler = view.findViewById(R.id.divisions_recycler)
        layout = view.findViewById(R.id.divisions_fragment_layout)
        animations.fadeIn(layout)
//        layoutFadeIn()
//        playAnimation(layout)
        initDivisionRecycler()
        subscribeToDivisions()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun initDivisionRecycler(){
        divisionAdapter = DivisionsAdapter(object : DivisionsClickListener {
            override fun onItemClick(alias: String, name: String) {
                navigateToLevelsFragment(alias, name)
            }
        })
        divisionRecycler.adapter = divisionAdapter
        divisionRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToDivisions(){
        lifecycleScope.launch {
            viewModel.divisions.collect {
                divisionAdapter.data = it
                if(it.isNotEmpty()){
                    animations.fadeIn(divisionRecycler)
//                    playAnimation(divisionRecycler)
                }
            }
        }
    }

//    private fun playAnimation(view: View){
//        val animation = AlphaAnimation(0f, 1f).apply {
//            duration = 500L
//            fillAfter = true
//        }
//        view.startAnimation(animation)
//    }

//    private fun  layoutFadeIn(){
//        animation.fadeIn(layout)
//    }


    private fun navigateToLevelsFragment(alias: String, name: String) {
        findNavController().navigate(DivisionsFragmentDirections.actionDivisionsToLevels(
            alias = alias,
            name = name
        ))
    }
}