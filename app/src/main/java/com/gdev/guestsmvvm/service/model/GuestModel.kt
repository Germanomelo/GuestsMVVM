package com.gdev.guestsmvvm.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest")
class GuestModel {
    constructor(id: Int, name: String, presence: Boolean) {
        this.id = id
        this.name = name
        this.presence = presence
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = true
}
