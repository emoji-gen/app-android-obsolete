package moe.pine.emoji.util

import com.squareup.otto.Bus

/**
 * Extensions for BusUtils
 * Created by pine on May 14, 2017.
 */

val Any.eventBus: Bus get() = BusUtils.INSTANCE
