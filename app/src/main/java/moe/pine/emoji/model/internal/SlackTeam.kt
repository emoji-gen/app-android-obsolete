package moe.pine.emoji.model.internal

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Model for slack team
 * Created by pine on May 14, 2017.
 */
@RealmClass
class SlackTeam(
        @PrimaryKey var id: String = UUID.randomUUID().toString(),
        var domain: String,
        var email: String,
        var password: String,
        var createdAt: Date = Date()
) : RealmObject()