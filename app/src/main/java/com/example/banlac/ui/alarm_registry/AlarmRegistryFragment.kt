package com.example.banlac.ui.alarm_registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banlac.R
import com.example.banlac.databinding.FragmentAlarmRegistryBinding

class AlarmRegistryFragment : Fragment() {

    private var _binding: com.example.banlac.databinding.FragmentAlarmRegistryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmRegistryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textInputEvent2.setOnClickListener { buttonView ->
            showPopupMenuEvent(buttonView)
        }

        binding.textInputNotify2.setOnClickListener { buttonView ->
            showPopupMenuNotify(buttonView)
        }

        binding.radio.setOnCheckedChangeListener {group, checkedId ->
            binding.linearLayoutRepeat.visibility = if (checkedId == R.id.rbRepeat) View.VISIBLE else View.INVISIBLE
            binding.linearLayoutTime.visibility = if (checkedId == R.id.rbTime) View.VISIBLE else View.INVISIBLE
        }

        binding.buttonSave.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showPopupMenuEvent(view: View) {
        val themedContext = ContextThemeWrapper(requireContext(), R.style.CustomPopupMenu)

        val popupMenu = PopupMenu(themedContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_alarm_event, popupMenu.menu)

        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                .invoke(mPopup, true)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.opcion_extract -> {
                        binding.textInputEvent2.setText(menuItem.title)
                        true
                    }
                    R.id.opcion_breastfeeding -> {
                        binding.textInputEvent2.setText(menuItem.title)
                        true
                    }
                    else -> false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        popupMenu.show()
    }

    private fun showPopupMenuNotify(view: View) {
        val themedContext = ContextThemeWrapper(requireContext(), R.style.CustomPopupMenu)

        val popupMenu = PopupMenu(themedContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_alarm_notification, popupMenu.menu)

        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.javaPrimitiveType)
                .invoke(mPopup, true)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.opcion_alarm -> {
                        binding.textInputNotify2.setText(menuItem.title)
                        true
                    }
                    R.id.opcion_vibrate -> {
                        binding.textInputNotify2.setText(menuItem.title)
                        true
                    }
                    R.id.opcion_music -> {
                        binding.textInputNotify2.setText(menuItem.title)
                        true
                    }
                    else -> false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        popupMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}