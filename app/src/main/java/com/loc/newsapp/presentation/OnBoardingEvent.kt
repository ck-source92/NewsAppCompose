package com.loc.newsapp.presentation

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}