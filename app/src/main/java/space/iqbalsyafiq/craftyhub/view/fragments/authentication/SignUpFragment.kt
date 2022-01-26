package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.FragmentSignUpBinding
import space.iqbalsyafiq.craftyhub.utils.Util.Companion.setEditTextView

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

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
    }

    private fun setViewEvent() {
        with(binding) {
            btnSignUp.setOnClickListener {
                activity?.onBackPressed()
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