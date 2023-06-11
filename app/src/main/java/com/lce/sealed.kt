package com.lce

sealed class Status
object LOADING : Status()
object SUCCESS : Status()
object FAILURE : Status()
object EMPTY : Status()

