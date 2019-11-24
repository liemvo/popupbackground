package com.liemvo.popup

enum class AnchorDirection(val value: String) {
    BOTTOM("bottom"), LEFT("left"), RIGHT("right"), TOP("top");
    
    companion object {
        fun of(value: String): AnchorDirection = if (value == TOP.value) {
            TOP
        } else if (value == LEFT.value) {
            LEFT
        } else if (value == RIGHT.value) {
            RIGHT
        } else {
            BOTTOM
        }
    }
}
