package com.adityagupta.gdsc_nie.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamMemberInfo(
    var name: String,
    var position: String,
    var github: String,
    var instagram: String,
): Parcelable
