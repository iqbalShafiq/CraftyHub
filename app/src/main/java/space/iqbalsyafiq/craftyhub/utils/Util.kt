package space.iqbalsyafiq.craftyhub.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import space.iqbalsyafiq.craftyhub.databinding.LayoutEditTextGeneralBinding

class Util {
    companion object {
        fun setIconColor(ivIcon: ImageView, status: String) {
            if (status == "active") {
                ivIcon.backgroundTintList = ColorStateList
                    .valueOf(Color.parseColor("#FF000000"))
            } else {
                ivIcon.backgroundTintList = ColorStateList
                    .valueOf(Color.parseColor("#FF889196"))
            }
        }

        fun setEditTextView(
            layout: LayoutEditTextGeneralBinding,
            hintText: String,
            resId: Int,
            inputType: String = "text"
        ) {
            layout.apply {
                // set icon
                ivIcon.setBackgroundResource(resId)
                setIconColor(ivIcon, " ")

                // set input type
                when (inputType) {
                    "password" -> etText.inputType =
                        InputType.TYPE_CLASS_TEXT or
                                InputType.TYPE_TEXT_VARIATION_PASSWORD
                    "email" -> etText.inputType =
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }

                // set text
                etText.hint = hintText
                etText.addTextChangedListener {
                    if (etText.text.isNotBlank()) {
                        setIconColor(ivIcon, "active")
                    } else {
                        setIconColor(ivIcon, " ")
                    }
                }
            }
        }
    }
}