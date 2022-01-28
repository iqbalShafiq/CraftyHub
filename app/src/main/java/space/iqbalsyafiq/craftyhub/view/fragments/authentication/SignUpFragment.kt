package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.FragmentSignUpBinding
import space.iqbalsyafiq.craftyhub.utils.Util.Companion.setEditTextView
import space.iqbalsyafiq.craftyhub.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set edit text view
        initiateEditTextView()

        // set view event
        setViewEvent()

        // observe viewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.success.observe(viewLifecycleOwner) { isSuccess ->
            isSuccess?.let {
                if (isSuccess) {
                    Toast
                        .makeText(
                            requireContext(),
                            "User Registered, let's login!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    activity?.onBackPressed()
                } else {
                    Toast
                        .makeText(
                            requireContext(),
                            "Please check again the form!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                with(binding) {
                    progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
                    btnSignUp.visibility = if (isLoading) View.GONE else View.VISIBLE
                    btnBack.visibility = if (isLoading) View.GONE else View.VISIBLE
                }
            }
        }
    }

    private fun setViewEvent() {
        with(binding) {
            btnSignUp.setOnClickListener {
                val fullName = layoutFullname.etText.text.toString()
                val userName = layoutUsername.etText.text.toString()
                val email = layoutEmail.etText.text.toString()
                val password = layoutPassword.etText.text.toString()
                val confirmationPassword = layoutConfirmationPassword.etText.text.toString()

                if (
                    fullName.isNotBlank() &&
                    userName.isNotBlank() &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    confirmationPassword.isNotBlank() &&
                    password == confirmationPassword
                ) {
                    viewModel.registerUser(email, password)
                }
            }

            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initiateEditTextView() {
        with(binding) {
            setEditTextView(
                layoutFullname,
                "Full Name",
                R.drawable.ic_fullname
            )

            setEditTextView(
                layoutUsername,
                "Username",
                R.drawable.ic_username
            )

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

            setEditTextView(
                layoutConfirmationPassword,
                "Confirmation Password",
                R.drawable.ic_password,
                "password"
            )
        }
    }
}