package com.leadingspark.mvvmpracticesession.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val phone_number: String? = null,
    val profile_picture: String? = null,
    val total_rewards: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}