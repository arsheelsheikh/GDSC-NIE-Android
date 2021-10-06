package com.adityagupta.gdsc_nie.data.remote.FeaturedData

import com.adityagupta.gdsc_nie.data.remote.PastEventsData.RecyclerData

data class Response(
    var featuredEvent: List<FeaturedEventData>? = null,
    var exception: Exception? = null
)
