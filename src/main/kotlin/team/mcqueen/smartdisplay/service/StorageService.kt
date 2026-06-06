package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.domain.storage.StorageInfo

interface StorageService {
    fun getStorageInfo(houseId: Long): StorageInfo
}