package com.adityagupta.gdsc_nie.data.remote.PastEventsData

data class Response(
    var pastEvents: List<RecyclerData>? = null,
    var exception: Exception? = null
)
