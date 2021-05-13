package com.example.latber.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.security.KeyStore
import java.util.jar.Attributes

class Textviewboldsplash(context: Context,attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init{
        applyFont()
    }

    private fun applyFont() {
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets,"Rolling-bold.ttf")
        setTypeface(typeface)
    }
}