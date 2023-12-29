package net.rishiz.acharyaprashant.utils

import kotlinx.coroutines.flow.Flow

/**
 * This interface is to kepp track of connection states
 */
interface ConnectivityObserver {
    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}