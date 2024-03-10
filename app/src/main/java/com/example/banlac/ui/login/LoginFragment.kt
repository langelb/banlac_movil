package com.example.banlac.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banlac.MainActivity
import com.example.banlac.R
import com.example.banlac.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity?)?.hideActionBar()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el listener del botón de login
        binding.btnSignIn.setOnClickListener {
            (activity as MainActivity?)?.showActionBar()
            findNavController().navigate(R.id.action_nav_login_to_nav_extraction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity?)?.showActionBar()
        _binding = null
    }
}
