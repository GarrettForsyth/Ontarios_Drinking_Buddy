package com.example.core.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.LCBOItemContract
import com.google.gson.annotations.SerializedName

/**
 * EXAMPLE API RESPONSE:
 * {"status":200,"message":null,"result":
 *  { "id":438457,
 *    "is_dead":false,
 *    "name":"Longslice Hopsta La Vista",
 *    "tags":"longslice hopsta la vista beer ale canada ontario brewery inc can",
 *    "is_discontinued":false,
 *    "price_in_cents":315,
 *    "regular_price_in_cents":315,
 *    "limited_time_offer_savings_in_cents":0,
 *    "limited_time_offer_ends_on":null,
 *    "bonus_reward_miles":0,
 *    "bonus_reward_miles_ends_on":null,
 *    "stock_type":"LCBO",
 *    "primary_category":"Beer",
 *    "secondary_category":"Ale",
 *    "origin":"Canada, Ontario",
 *    "package":"473 mL can",
 *    "package_unit_type":"can",
 *    "package_unit_volume_in_milliliters":473,
 *    "total_package_units":1,
 *    "volume_in_milliliters":473,
 *    "alcohol_content":650,
 *    "price_per_liter_of_alcohol_in_cents":1024,
 *    "price_per_liter_in_cents":665,
 *    "inventory_count":1946,
 *    "inventory_volume_in_milliliters":920458,
 *    "inventory_price_in_cents":612990,
 *    "sugar_content":null,
 *    "producer_name":"Longslice Brewery Inc",
 *    "released_on":null,
 *    "has_value_added_promotion":false,
 *    "has_limited_time_offer":false,
 *    "has_bonus_reward_miles":false,
 *    "is_seasonal":false,
 *    "is_vqa":false,
 *    "is_ocb":false,
 *    "is_kosher":false,
 *    "value_added_promotion_description":null,
 *    "description":null,
 *    "serving_suggestion":"Serve with a burger topped with caramelized onions, or grilled shrimp and guacamole.",
 *    "tasting_note":"Gold medal winner at the 2015 and 2017 Ontario Brewing Awards. In the glass, a hazy copper colour, with a generous lacy head. A rich malty nose is punctuated by forward hops character, contributing citrus and herbal notes. Balanced on the palate, with toasty and malty flavours leading to a lengthy and refreshing finish.",
 *    "updated_at":"2019-01-21T14:32:16.313Z",
 *    "image_thumb_url":"https://dx5vpyka4lqst.cloudfront.net/products/438457/images/thumb.png",
 *    "image_url":"https://dx5vpyka4lqst.cloudfront.net/products/438457/images/full.jpeg",
 *    "varietal":"American Ipa",
 *    "style":"Medium \u0026 Hoppy",
 *    "tertiary_category":"India Pale Ale (IPA)",
 *    "sugar_in_grams_per_liter":null,
 *    "clearance_sale_savings_in_cents":0,
 *    "has_clearance_sale":false,
 *    "product_no":438457
 *    }
 */
@Entity(tableName = LCBOItemContract.TABLE_NAME)
data class LCBOItem(

    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @SerializedName(LCBOItemContract.COLUMN_NAME)
    @ColumnInfo(name = LCBOItemContract.COLUMN_NAME)
    var name: String,

    @SerializedName(LCBOItemContract.COLUMN_TAGS)
    @ColumnInfo(name = LCBOItemContract.COLUMN_TAGS)
    var tags: String?,

    @SerializedName("price_in_cents")
    var priceInCents: Int?,

    @SerializedName("regular_price_in_cents")
    var regularPriceInCents: Int?,

    @SerializedName(LCBOItemContract.COLUMN_PRIMARY_CATEGORY)
    @ColumnInfo(name = LCBOItemContract.COLUMN_PRIMARY_CATEGORY)
    var primaryCategory: String?, // TODO Get all categories(!?)

    @SerializedName(LCBOItemContract.COLUMN_SECONDARY_CATEGORY)
    @ColumnInfo(name = LCBOItemContract.COLUMN_SECONDARY_CATEGORY)
    var secondaryCategory: String?,

    @SerializedName(LCBOItemContract.COLUMN_TERTIARY_CATEGORY)
    @ColumnInfo(name = LCBOItemContract.COLUMN_TERTIARY_CATEGORY)
    var tertiaryCategory: String?,

    @SerializedName("origin")
    var origin: String, // TODO Get all origins (!?)
    var packageUnitType: String?,

    @SerializedName("package_unit_volume_in_milliliters")
    var packageUnitVolumeInMilliliters: Int?,

    @SerializedName("total_package_units")
    var totalPackageUnits: Int?,

    @SerializedName("volume_in_milliliters")
    var volumeInMilliliters: Int?,

    @SerializedName("alcohol_content")
    var alcoholContent: Int?,

    @SerializedName("price_per_liter_of_alcohol_in_cents")
    var pricePerLiterOfAlcoholInCents: Int?,

    @SerializedName("price_per_liter_of_alcohol")
    var pricePerLiterInCents: Int?,

    @SerializedName("sugar_conent")
    var sugarContent: Int?,

    @SerializedName("producer_name")
    var producerName: String?,

    @SerializedName("released_on")
    var releasedOn: String?,

    @SerializedName("has_value_added_promotion")
    var hasValueAddedPromotion: Boolean?,

    @SerializedName("has_limited_time_offer")
    var hasLimitedTimeOffer: Boolean?,

    @SerializedName("is_seasonal")
    var isSeasonal: Boolean?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("serving_suggestion")
    var servingSuggestion: String?,

    @SerializedName("tasting_note")
    var tastingNote: String?,

    @SerializedName("image_thumb_url")
    var imageThumbUrl: String?,

    @SerializedName("image_url")
    var imageUrl: String?,

    @SerializedName("varietal")
    var varietal: String?,

    @SerializedName("style")
    var style: String?,

    @SerializedName("is_dead")
    var isDead: Boolean?,

    @SerializedName("is_discontinued")
    var isDiscontinued: Boolean?

)
