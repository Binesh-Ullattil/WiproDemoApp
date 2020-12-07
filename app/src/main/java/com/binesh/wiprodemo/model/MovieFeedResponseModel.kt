package com.binesh.wiprodemo.model

data class MovieFeedResponseModel(
    val rows: MutableList<Row>,
    val title: String
)