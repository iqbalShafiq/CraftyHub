package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.FragmentSuccessForgotPasswordBinding

class SuccessForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentSuccessForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuccessForgotPasswordBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set view event
        setViewEvent()

    }

    private fun setViewEvent() {
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack(R.id.loginFragment, false)
        }
    }
}