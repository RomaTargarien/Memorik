package com.house.memorik.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}