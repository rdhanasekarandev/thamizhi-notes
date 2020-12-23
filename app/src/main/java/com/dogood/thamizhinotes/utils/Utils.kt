package com.dogood.thamizhinotes.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dogood.thamizhinotes.R
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun showSnackBar(context: Context, rootView: View, msg: String, msg2: String): Snackbar {
            val font = ResourcesCompat.getFont(context, R.font.linetocircular)
            val text = getHtmlText(context, msg, msg2)
            val snackbar = Snackbar.make(rootView, fromHtml(text), Snackbar.LENGTH_INDEFINITE)
            val snackbarLayout = snackbar.view
            snackbarLayout.background = ContextCompat.getDrawable(context, R.color.white)
            val textView =
                snackbarLayout.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            with(textView) {
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear_semi_black, 0)
                compoundDrawablePadding =
                    17//resources.getDimensionPixelOffset(R.dimen.snackbar_icon_padding)
                typeface = font
                setTextColor(ContextCompat.getColor(context, R.color.black))
                setOnTouchListener { _, _ ->
                    snackbar.dismiss()
                    false
                }
            }
            snackbar.show()
            return snackbar
        }

        fun getHtmlText(context: Context, title: String, msg: String): String {
            return "<font color=${ContextCompat.getColor(
                context,
                R.color.snackbar_text_red
            )}>$title</font> <font color=${ContextCompat.getColor(
                context,
                R.color.text_color
            )}>$msg</font>"
        }

        fun fromHtml(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(html)
            }
        }
    }
}