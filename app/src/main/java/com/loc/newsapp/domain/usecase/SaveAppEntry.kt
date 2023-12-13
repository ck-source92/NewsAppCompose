package com.loc.newsapp.domain.usecase

import com.loc.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}