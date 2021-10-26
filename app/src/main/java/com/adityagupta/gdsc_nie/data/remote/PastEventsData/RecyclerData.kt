package com.adityagupta.gdsc_nie.data.remote.PastEventsData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecyclerData (
    val id: String? = "",
    val type: String? = "",
    val speaker: String? = "",
    val title: String? = "",
    val link: String? = "",
    val duration: String? = "",
    val expiry: String? = ""
        ): Parcelable