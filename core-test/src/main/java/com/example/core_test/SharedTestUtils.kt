package com.example.core_test

import com.example.core.vo.LCBOItem

fun listOfLcboItems(numberOfItems: Int): List<LCBOItem> {
    return (0..numberOfItems).toList()
        .map { createTestLCBOItem(it) }
}

fun createTestLCBOItem(id: Int): LCBOItem {
    return LCBOItem(
        id = id,
        name = "Test LCBO Item #{id}",
        tags = "TestTag1 TestTag2",
        priceInCents = 0,
        regularPriceInCents = 0,
        primaryCategory = "Test Primary Category",
        secondaryCategory = "Test Secondary Category",
        tertiaryCategory = "Test Tertiary Category",
        origin = "Test Origin",
        packageUnitType = "Test package unit type",
        packageUnitVolumeInMilliliters = 0,
        totalPackageUnits = 0,
        volumeInMilliliters = 0,
        alcoholContent = 0,
        pricePerLiterOfAlcoholInCents = 0,
        pricePerLiterInCents = 0,
        sugarContent = 0,
        producerName = "Test Producer Name",
        releasedOn = "Test release date",
        hasValueAddedPromotion = false,
        hasLimitedTimeOffer = false,
        isSeasonal = false,
        description = "Test description",
        servingSuggestion = "Test Serving Suggestion",
        tastingNote = "Test tasting note",
        imageThumbUrl = "Test image thumbnail",
        imageUrl = "Test image",
        style = "Test style",
        varietal = "Test varietal",
        isDead = false,
        isDiscontinued = false
    )
}