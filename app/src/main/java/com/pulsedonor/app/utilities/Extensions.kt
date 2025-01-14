package com.pulsedonor.app.utilities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.pulsedonor.app.R
import com.pulsedonor.app.utilities.glide.GlideApp
import io.github.muddz.styleabletoast.StyleableToast
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

var token: String = ""

fun isEmailValid(email: String): Boolean {
    return Pattern.compile(
        "^[_A-Za-z0-9-\\+\\.\\_\\-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(email).matches()
}

fun checkEmailLastChar(email: String): Boolean {
    val emailRecipientName = email.substring(0, email.indexOf("@"))
    var emailLastChar = ""
    for (i in emailRecipientName.indices) {
        if (i == emailRecipientName.length - 1) {
            emailLastChar = emailRecipientName[i].toString()
        }
    }
    return emailLastChar == "+" || emailLastChar == "." || emailLastChar == "-"
}

fun checkEmailFirstChar(email: String): Boolean {
    val emailFirstChar = email.substring(0, 1)
    val emailCharArray = email.toCharArray()

    return emailFirstChar == "." || emailFirstChar == "+" || emailFirstChar == "-" || emailCharArray[0].isDigit()
}

fun formatNumbers(decNumber: Double): String {
    val nf: NumberFormat = NumberFormat.getInstance(Locale("de", "CH"))
    nf.maximumFractionDigits = 2
    nf.minimumFractionDigits = 2
    val formattedNumber = nf.format(decNumber)

    // Check if the decimal part consists of two zeros
    val decimalPart = formattedNumber.substringAfterLast('.')
    if (decimalPart == "00") {
        // If the decimal part is "00", remove it and append ".-"
        return formattedNumber.substringBefore('.') + ".-"
    } else {
        // Otherwise, return the formatted number as usual
        return formattedNumber
    }
}

@SuppressLint("SimpleDateFormat")
fun calculateAge(birthdateString: String): Int {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val birthdate = dateFormat.parse(birthdateString)
        val birthCalendar = Calendar.getInstance().apply {
            time = birthdate
        }
        val now = Calendar.getInstance()
        var age = now.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

        if (now.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        age
    } catch (e: Exception) {
        e.printStackTrace()
        0 // or any default value to indicate failure
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentYear(): String {
    val currentYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
    return currentYear.format(Date())
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(message: String) {
    StyleableToast
        .Builder(this)
        .text(message)
        .iconStart(R.mipmap.ic_launcher_round)
        .textColor(Color.WHITE)
        .cornerRadius(50)
        .backgroundColor(this.getColor(R.color.cl_2b2b2b))
        .show()
}

fun openActivity(context: Context, activity: Activity) {
    val intent = Intent(context, activity::class.java)
    context.startActivity(intent)
}

fun ImageView.loadUrl(url: String, placeholder: Int, loader: ProgressBar?) {
    if (loader == null) {
        GlideApp.with(context).load(url).placeholder(placeholder)
            .apply(RequestOptions.fitCenterTransform())
            .into(this)
    } else {
        val glideListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loader.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loader.visibility = View.GONE
                return false
            }

        }

        GlideApp.with(context).load(url).placeholder(placeholder).listener(glideListener)
            .apply(RequestOptions.fitCenterTransform())
            .into(this)
    }
}