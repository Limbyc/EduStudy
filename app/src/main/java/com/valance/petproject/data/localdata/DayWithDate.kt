package com.valance.petproject.data.localdata

import com.valance.petproject.domain.DayOfWeek
import java.time.LocalDate

data class DayWithDate(val dayOfWeek: DayOfWeek, val date: LocalDate)