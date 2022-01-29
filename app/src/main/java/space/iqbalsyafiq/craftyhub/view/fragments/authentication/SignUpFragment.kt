package space.iqbalsyafiq.craftyhub.view.fragments.authentication

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import space.iqbalsyafiq.craftyhub.R
import space.iqbalsyafiq.craftyhub.databinding.DialogPictureBinding
import space.iqbalsyafiq.craftyhub.databinding.FragmentSignUpBinding
import space.iqbalsyafiq.craftyhub.utils.Util.Companion.setEditTextView
import space.iqbalsyafiq.craftyhub.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by activityViewModels()

    companion object {
        const val OPEN_CAMERA = 0
        const val OPEN_GALLERY = 1
    }

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
            containerPhoto.setOnClickListener {
                showDialog()
            }

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

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogPictureBinding.inflate(layoutInflater)

        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(dialogBinding.root)
        }

        with(dialogBinding) {
            ivCamera.setOnClickListener {
                getPicture(OPEN_CAMERA)
                dialog.dismiss()
            }

            ivGallery.setOnClickListener {
                getPicture(OPEN_GALLERY)
                dialog.dismiss()
            }
        }

        dialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
            window?.setLayout(800, 600)
        }
    }

    private fun getPicture(openCode: Int) {
        when (openCode) {
            OPEN_CAMERA -> {
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, OPEN_CAMERA)
            }

            OPEN_GALLERY -> {
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, OPEN_GALLERY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            OPEN_CAMERA -> if (resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImage: Bitmap? = imageReturnedIntent?.extras?.get("data") as Bitmap
                applyImage(selectedImage)
            }
            OPEN_GALLERY -> if (resultCode == AppCompatActivity.RESULT_OK) {
                val selectedImage = imageReturnedIntent?.data
                Log.d("SignUpFragment", "onActivityResult: $selectedImage")
                applyImage(selectedImage)
            }
        }
    }

    private fun applyImage(selectedImage: Any?) {
        with(binding) {
            Glide.with(requireContext())
                .asBitmap()
                .load(selectedImage)
                .circleCrop()
                .into(ivPhotoProfile)
            ivPhotoProfile.setBackgroundResource(R.drawable.bg_circle)
            tvAddPhoto.visibility = View.GONE
            ivAddPhoto.visibility = View.GONE
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