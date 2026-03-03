package com.example.timeapp

internal class LinkedList {
    private var head: Node? = null
    private var tail: Node? = null

    private class Node
        (var value: Int) {
        var index: Int = 0
        var next: Node? = null
        var last: Node? = null
    }

    fun add(value: Int) {
        val node: Node = LinkedList.Node(value)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail!!.next = node
            node.last = tail
            tail = node
        }
    }

    fun print() {
        var current = head
        while (current != null) {
            println(current.value)
            current = current.next
        }
    }

    fun remove(value: Int) {
        var current = head
        while (current != null) {
            if (current.value == value) {
                if (current.last != null) {
                    current.last!!.next = current.next
                } else {
                    head = current.next
                }
                if (current.next != null) {
                    current.next!!.last = current.last
                } else {
                    tail = current.last
                }
            }
            current = current.next
        }
    }
}