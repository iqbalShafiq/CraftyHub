package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.FragmentForgotPasswordBinding
import space.iqbalsyafiq.craftyhub.utils.Util.Companion.setEditTextView

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initiate edit text view
        initiateEditTextView()

        // set view event
        setViewEvent()
    }

    private fun setViewEvent() {
        with(binding) {
            btnResetPassword.setOnClickListener {
                val action =
                    ForgotPasswordFragmentDirections.navigateToSuccessForgotPasswordFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }

            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initiateEditTextView() {
        setEditTextView(
            binding.layoutEmail,
            "Email",
            R.drawable.ic_email,
            "email"
        )
    }

}