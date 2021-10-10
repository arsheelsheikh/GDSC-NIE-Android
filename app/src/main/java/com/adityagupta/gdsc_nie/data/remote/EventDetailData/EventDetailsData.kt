package com.adityagupta.gdsc_nie.data.remote.EventDetailData

import com.google.gson.annotations.SerializedName

data class EventDetailsData (
    val id: String? = "",
    val description: String? = "",
    val speaker1link: String? = "",
    val speaker1title: String? = "",
    val speaker2link: String? = "",
    val speaker2title: String? = "",
    val speaker3link: String? = "",
    val speaker3title: String? = "",
)