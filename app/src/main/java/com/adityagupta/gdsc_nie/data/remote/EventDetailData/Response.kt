package com.adityagupta.gdsc_nie.data.remote.EventDetailData

import com.adityagupta.gdsc_nie.data.remote.FeaturedData.FeaturedEventData

class Response(
    var eventDetails: List<EventDetailsData>? = null,
    var exception: Exception? = null
)
