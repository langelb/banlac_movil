package com.example.banlac.ui.extraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banlac.MainActivity
import com.example.banlac.R
import com.example.banlac.databinding.FragmentExtractionBinding

class ExtractionFragment : Fragment() {

    private var _binding: FragmentExtractionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val extractionViewModel =
            ViewModelProvider(this).get(ExtractionViewModel::class.java)

        _binding = FragmentExtractionBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        extractionViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.lockDrawer(true)
//        mainActivity.setFabVisibility(true)

        binding.iconoCelda14.setOnClickListener { buttonView ->
            showPopupMenu(buttonView)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_extraction_to_nav_extraction_registry)
        }
    }

    private fun showPopupMenu(view: View) {
        val themedContext = ContextThemeWrapper(requireContext(), R.style.CustomPopupMenu)

        val popupMenu = PopupMenu(themedContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_extraction, popupMenu.menu)

        // Intenta mostrar los íconos en el menú emergente
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                .invoke(mPopup, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.opcion_editar -> {
                    findNavController().navigate(R.id.action_nav_extraction_to_nav_extraction_edit)
                    true
                }
                R.id.opcion_eliminar -> {
                    showDeleteConfirmationDialog()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun showDeleteConfirmationDialog() {
        // Inflar el layout personalizado
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)

        // Crear el AlertDialog
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        // Establecer manejadores de clic para los botones
        dialogView.findViewById<Button>(R.id.button_no).setOnClickListener {
            // Manejar la acción de "No"
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.button_yes).setOnClickListener {
            // Manejar la acción de "Sí"
            val rowToRemove = binding.tableRowDelete
            binding.tableLayoutData.removeView(rowToRemove)
            dialog.dismiss()
        }

        // Mostrar el AlertDialog
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Fondo transparente
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}