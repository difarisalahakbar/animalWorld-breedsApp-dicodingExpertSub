package com.example.core

import com.example.core.data.source.local.entity.ImagesEntity
import com.example.core.domain.model.Images
import com.example.core.utils.Helper
import org.junit.Assert
import org.junit.Test

class HelperTest {
    private lateinit var imagesEntity: ArrayList<ImagesEntity>
    private lateinit var imagesDomain: ArrayList<Images>

    @Test
    fun testMapEntitiesImagesToDomain() {
        imagesEntity = ArrayList()
        imagesEntity.add(ImagesEntity("1", "test"))
        imagesEntity.add(ImagesEntity("2", "test2"))

        imagesDomain = ArrayList()
        imagesDomain.add(Images("1", "test"))
        imagesDomain.add(Images("2", "test2"))

        Assert.assertEquals(imagesDomain, Helper.mapEntitiesImagesToDomain(imagesEntity))
    }
}
