package com.example.banlac.ui.alarm

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
import com.example.banlac.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.lockDrawer(true)

        binding.iconoCelda14.setOnClickListener { buttonView ->
            showPopupMenu(buttonView)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_alarm_to_nav_alarm_registry)
        }
    }

    private fun showPopupMenu(view: View) {
        val themedContext = ContextThemeWrapper(requireContext(), R.style.CustomPopupMenu)

        val popupMenu = PopupMenu(themedContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_alarm, popupMenu.menu)

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
                    findNavController().navigate(R.id.action_nav_alarm_to_nav_alarm_edit)
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
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.button_no).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.button_yes).setOnClickListener {
            val rowToRemove = binding.tableRowDelete
            binding.tableLayoutData.removeView(rowToRemove)
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}