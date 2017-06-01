package moe.pine.emoji.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

/**
 * Model for history
 * Created by pine on 2017/06/01.
 */
@RealmClass
class History(
        @PrimaryKey var id: String = UUID.randomUUID().toString(),
        var emojiName: String,
        var emojiUri: String,
        var createdAt: Date = Date()
) : RealmObject()