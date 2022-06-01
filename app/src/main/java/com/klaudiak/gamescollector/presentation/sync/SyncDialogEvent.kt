package com.klaudiak.gamescollector.presentation.sync



sealed class SyncDialogEvent{
    object OnYesClickedEvent : SyncDialogEvent()
    object OnCancelClickedEvent: SyncDialogEvent()
}