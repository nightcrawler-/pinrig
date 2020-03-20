package com.cafrecode.pinrig

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.cafrecode.clamnumpad.enums.KeypadButtonEnum
import com.cafrecode.clamnumpad.interfaces.KeypadButtonClickedListener
import com.cafrecode.clamnumpad.views.KeypadView


/**
 * Should accept a keypad as a constructor/setup param
 * Listen to keypad input
 * Update UI with filled input or cleared input for 4 digit pins
 * return/post value when input is complete to be handled by 'hosting' party
 */
class PinRig(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
    KeypadButtonClickedListener {

    lateinit var listener: PinRigListener
    lateinit var keypad: KeypadView

    private val parent: PinRig
    private val holder: LinearLayout

    private var currentPin = ""

    private val imageViews = arrayOfNulls<ImageView>(MAX_LENGTH)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        parent = inflater.inflate(R.layout.pin_rig, this) as PinRig
        holder = parent.findViewById<View>(R.id.holder) as LinearLayout
        populateImageViews()
    }

    //getter setter/builder for keypad?
    fun setup() {
        //keypad inited and not nul, check
        keypad.setKeypadButtonClickedListener(this)
    }

    private fun clearRig() {
        for (i in 0 until MAX_LENGTH) {
            imageViews[i]?.setImageResource(R.drawable.pin_rect_idle)
        }
    }

    private fun fillRig(size: Int) {
        clearRig()
        for (i in 0..size) {
            imageViews[i]?.setImageResource(R.drawable.pin_rect_filled)
        }
    }

    /**
     * Get the max length image views -- findViewById things, based on their location as chuldren
     */
    private fun populateImageViews() {
        for (i in 0 until holder.childCount) {
            if (holder.getChildAt(i) is ImageView)
                imageViews[i] = holder.getChildAt(i) as ImageView
        }

    }

    /**
    //when keypad input detected do:
    //check input type -- clear of digit
    //check for current pin length + current index, if max, ignore input, call pin complete (review)
    //check if input or clear
    //adjust index (eith add or sub)
    //adjust current pint (add or sub)
    //populate mask based on*
     */
    override fun onKeypadClick(keyboardButtonEnum: KeypadButtonEnum?) {

        if (keyboardButtonEnum?.buttonValue == KeypadButtonEnum.BUTTON_CLEAR.buttonValue) {
            //check current pin length
            if (currentPin.isNotEmpty()) {
                currentPin = currentPin.substring(0, currentPin.length - 1)
            }
            //reduce count if greater than 0
            //trunc current pin by one

        } else {
            //check pin length
            //add one if less than max, add digit to pin
            //if max, call complete with current

            if (currentPin.length < MAX_LENGTH) {
                currentPin += keyboardButtonEnum?.buttonValue
            }

            if (currentPin.length == MAX_LENGTH ) {
                listener.onPinComplete(currentPin)
            }
        }

        fillRig(currentPin.length - 1)
    }

    override fun onRippleAnimationEnd() {
    }

    companion object {
        const val MAX_LENGTH = 4
    }

    /**
     * Hosts have to implement this method to be furnished with the complete PIN
     */
    interface PinRigListener {
        fun onPinComplete(pin: String)
    }
}
