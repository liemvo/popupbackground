package com.vad.popup.demo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.liemvo.popup.AnchorDirection
import com.liemvo.popup.PopupBackground
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.spinner
import kotlinx.android.synthetic.main.activity_main.textBottomPopup
import kotlinx.android.synthetic.main.activity_main.textLeftPopup
import kotlinx.android.synthetic.main.activity_main.textRightPopup
import kotlinx.android.synthetic.main.activity_main.textTopPopup

class MainActivity : AppCompatActivity() {
    
    private val directions = listOf(
        AnchorDirection.TOP.value.capitalize(),
        AnchorDirection.LEFT.value.capitalize(),
        AnchorDirection.RIGHT.value.capitalize(),
        AnchorDirection.BOTTOM.value.capitalize()
    )
    
    private val popupViews by lazy {
        listOf(
            textBottomPopup,
            textRightPopup,
            textLeftPopup,
            textTopPopup
        )
    }
    
    private var selectedPosition = 0
    
    private val currentView: View
        get() = popupViews[selectedPosition]
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureSpinner()
        
        textBottomPopup.background =
            PopupBackground(color = Color.MAGENTA, direction = AnchorDirection.TOP)
        textTopPopup.background =
            PopupBackground(color = Color.MAGENTA, direction = AnchorDirection.BOTTOM)
        textLeftPopup.background =
            PopupBackground(color = Color.MAGENTA, direction = AnchorDirection.RIGHT)
        textRightPopup.background =
            PopupBackground(color = Color.MAGENTA, direction = AnchorDirection.LEFT)
        button.setOnClickListener {
            showOrHideView(currentView)
        }
    }
    
    private fun showOrHideView(view: View) {
        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
    
    private fun configureSpinner() {
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, directions)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapter: AdapterView<*>?) {
            
            }
    
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPosition = position
                popupViews.forEach { it.visibility = View.GONE }
            }
        }
    }
}
