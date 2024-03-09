package com.example.banlac.ui.extraction_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banlac.databinding.FragmentExtractionEditBinding

class ExtractionEditFragment : Fragment() {

    private var _binding: FragmentExtractionEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val extractionRegistryViewModel =
            ViewModelProvider(this).get(ExtractionEditViewModel::class.java)

        _binding = FragmentExtractionEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aquí estableces el listener del botón para que vuelva al Fragmento A
        binding.buttonSave.setOnClickListener {
            // Puedes usar el método 'popBackStack()' para volver al fragmento anterior
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}