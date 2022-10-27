package com.house.memorik.utils.ext

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavController.navigateWithClearingBackStack(route: String) {
    navigate(route = route) {
        popUpTo(graph.findStartDestination().id){
            inclusive = true
        }
    }
    graph.setStartDestination(route)
}