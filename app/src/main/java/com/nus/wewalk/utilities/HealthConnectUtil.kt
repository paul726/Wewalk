package com.nus.wewalk.utilities

import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.nus.wewalk.WewalkApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant

val healthPermissions = setOf(
    "android.permission.health.READ_STEPS"
)

fun checkAllPermissionGrand(callback: (result: Boolean) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val result = WewalkApplication.healthConnectClient.permissionController.getGrantedPermissions()
        withContext(Dispatchers.Main) {
            callback.invoke(result.containsAll(healthPermissions))
        }
    }
}

fun fetchWalkRecord(startTime: Instant,
                    endTime: Instant, callback:(records: List<StepsRecord>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val result = WewalkApplication.healthConnectClient.permissionController.getGrantedPermissions()
        if (result.containsAll(healthPermissions)) {
            val response = WewalkApplication.healthConnectClient.readRecords(
                ReadRecordsRequest(StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.Companion.between(startTime, endTime)))
            withContext(Dispatchers.Main) {
                callback.invoke(response.records)
            }
        }
    }
}