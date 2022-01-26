package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.FragmentLoginBinding
import space.iqbalsyafiq.craftyhub.utils.Util.Companion.setEditTextView

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initiate edit text
        initiateEditTextView()

        // set view event
        setViewEvent()
    }

    private fun setViewEvent() {
        with(binding) {
            btnSignUp.setOnClickListener {
                val action = LoginFragmentDirections.navigateToSignUpFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }

            tvForgotPassword.setOnClickListener {
                val action = LoginFragmentDirections.navigateToForgotPasswordFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }

    private fun initiateEditTextView() {
        with(binding) {
            setEditTextView(
                layoutEmail,
                "Email",
                R.drawable.ic_email,
                "email"
            )

            setEditTextView(
                layoutPassword,
                "Password",
                R.drawable.ic_password,
                "password"
            )
        }
    }
}