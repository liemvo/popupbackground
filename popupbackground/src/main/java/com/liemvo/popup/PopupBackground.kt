package com.liemvo.popup

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.drawable.Drawable

class PopupBackground(
    private val topLeftRadius: Int,
    private val topRightRadius: Int,
    private val bottomLeftRadius: Int,
    private val bottomRightRadius: Int,
    private val sidePadding: Int = 8.dpToPx(),
    private val color: Int = Color.WHITE,
    private val direction: AnchorDirection = AnchorDirection.BOTTOM
) : Drawable() {
    
    constructor(
        sidePadding: Int = 8.dpToPx(),
        color: Int = Color.BLUE,
        radius: Int = 8.dpToPx(),
        direction: AnchorDirection = AnchorDirection.BOTTOM
    ) : this(
        radius,
        radius,
        radius,
        radius,
        sidePadding,
        color,
        direction
    )
    
    override fun setAlpha(alpha: Int) {}
    
    override fun getOpacity(): Int = PixelFormat.OPAQUE
    
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    
    override fun draw(canvas: Canvas) {
        val paint = Paint().apply {
            color = this@PopupBackground.color
        }
        
        val path = Path()
        when (direction) {
            AnchorDirection.BOTTOM -> {
                configureCenterBottomAnchor(path)
                configureBottomRectangle(path)
            }
            AnchorDirection.LEFT -> {
                configureCenterLeftAnchor(path)
                configureLeftRectangle(path)
            }
            AnchorDirection.RIGHT -> {
                configureCenterRightAnchor(path)
                configureRightRectangle(path)
            }
            AnchorDirection.TOP -> {
                configureCenterTopAnchor(path)
                configureTopRectangle(path)
            }
        }
        
        path.close()
        canvas.drawPath(path, paint)
    }
    
    private fun configureCenterBottomAnchor(path: Path) {
        val centerX = (bounds.left + bounds.right).toFloat() / 2
        val bottomY = (bounds.top + bounds.bottom).toFloat()
        path.moveTo(centerX, bottomY)
        path.lineTo(centerX - sidePadding.toFloat(), bottomY - sidePadding)
        path.lineTo(centerX + sidePadding.toFloat(), bottomY - sidePadding)
        path.lineTo(centerX, bottomY)
    }
    
    private fun configureBottomRectangle(path: Path) {
        configureRectangle(
            path,
            RectF(
                bounds.left.toFloat(),
                bounds.top.toFloat(),
                bounds.right.toFloat(),
                bounds.bottom.toFloat() - sidePadding
            )
        )
    }
    
    private fun configureCenterTopAnchor(path: Path) {
        val centerX = (bounds.left + bounds.right).toFloat() / 2
        val topY = bounds.top.toFloat()
        path.moveTo(centerX, topY)
        path.lineTo(centerX - sidePadding.toFloat(), topY + sidePadding)
        path.lineTo(centerX + sidePadding.toFloat(), topY + sidePadding)
        path.lineTo(centerX, topY)
    }
    
    private fun configureTopRectangle(path: Path) {
        configureRectangle(
            path,
            RectF(
                bounds.left.toFloat(),
                bounds.top.toFloat() + sidePadding,
                bounds.right.toFloat(),
                bounds.bottom.toFloat()
            )
        )
    }
    
    private fun configureCenterLeftAnchor(path: Path) {
        val distance = sidePadding.toFloat()
        val centerY = (bounds.top + bounds.bottom).toFloat() / 2
        val leftX = bounds.left.toFloat()
        path.moveTo(leftX, centerY)
        path.lineTo(leftX + distance, centerY - sidePadding)
        path.lineTo(leftX + distance, centerY + sidePadding)
        path.lineTo(leftX, centerY)
    }
    
    private fun configureLeftRectangle(path: Path) {
        configureRectangle(
            path,
            RectF(
                bounds.left.toFloat() + sidePadding,
                bounds.top.toFloat(),
                bounds.right.toFloat(),
                bounds.bottom.toFloat()
            )
        )
    }
    
    private fun configureCenterRightAnchor(path: Path) {
        val distance = sidePadding.toFloat()
        val centerY = (bounds.top + bounds.bottom).toFloat() / 2
        val rightX = (bounds.left + bounds.right).toFloat()
        path.moveTo(rightX, centerY)
        path.lineTo(rightX - distance, centerY - sidePadding)
        path.lineTo(rightX - distance, centerY + sidePadding)
        path.lineTo(rightX, centerY)
    }
    
    private fun configureRightRectangle(path: Path) {
        configureRectangle(
            path,
            RectF(
                bounds.left.toFloat(),
                bounds.top.toFloat(),
                bounds.right.toFloat() - sidePadding,
                bounds.bottom.toFloat()
            )
        )
    }
    
    private fun configureRectangle(path: Path, rect: RectF) {
        path.moveTo(rect.left + topLeftRadius / 2, rect.top)
        path.lineTo(rect.right - topRightRadius / 2, rect.top)
        path.quadTo(rect.right, rect.top, rect.right, rect.top + topLeftRadius / 2)
        path.lineTo(rect.right, rect.bottom - bottomRightRadius / 2)
        path.quadTo(rect.right, rect.bottom, rect.right - bottomRightRadius / 2, rect.bottom)
        path.lineTo(rect.left + bottomLeftRadius / 2, rect.bottom)
        path.quadTo(rect.left, rect.bottom, rect.left, rect.bottom - bottomLeftRadius / 2)
        path.lineTo(rect.left, rect.top + topLeftRadius / 2)
        path.quadTo(rect.left, rect.top, rect.left + topLeftRadius / 2, rect.top)
    }
}
