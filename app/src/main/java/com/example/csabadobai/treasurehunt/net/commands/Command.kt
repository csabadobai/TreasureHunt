package com.example.csabadobai.treasurehunt.net.commands

/**
 * Created by csaba.dobai on 03-11-2017.
 */
interface Command <T> {
    fun execute(): T
}