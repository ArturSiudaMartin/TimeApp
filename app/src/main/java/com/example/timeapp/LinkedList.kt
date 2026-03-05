package com.example.timeapp

class ScreenNode(val screen: Screen) {
    var next: ScreenNode? = null
    var prev: ScreenNode? = null
}

class ScreenManager {
    private var head: ScreenNode? = null
    private var current: ScreenNode? = null

    fun addScreen(screen: Screen) {
        val newNode = ScreenNode(screen)
        if (head == null) {
            head = newNode
            current = head
        } else {
            var tail = head
            while (tail?.next != null) tail = tail.next
            tail?.next = newNode
            newNode.prev = tail
        }
    }

    fun getCurrentScreen(): Screen? = current?.screen
    fun goNext(): Screen? {
        if (hasNext()) current = current?.next
        return current?.screen
    }

    fun goPrev(): Screen? {
        if (hasPrev()) current = current?.prev
        return current?.screen
    }
    fun hasNext(): Boolean = current?.next != null
    fun hasPrev(): Boolean = current?.prev != null
}