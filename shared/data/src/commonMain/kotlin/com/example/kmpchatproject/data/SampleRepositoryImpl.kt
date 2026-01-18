package com.example.kmpchatproject.data

import com.example.kmpchatproject.domain.SampleRepository

class SampleRepositoryImpl : SampleRepository {
    override fun getData(): String = "Data from Repository"
}
