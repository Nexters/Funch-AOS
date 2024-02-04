package com.moya.funch.mapper

// network response
data class DummyDto(
    val id: Int,
    val name: String,
)

// database entity
data class DummyEntity(
    val id: Int,
    val name: String,
)

// domain model
data class DummyDomain(
    val id: Int,
    val name: String,
)

// toDomain() 함수
fun DummyDto.toDomain(): DummyDomain =
    DummyDomain(
        id = this.id,
        name = name,
    )

// toDto() 함수
fun DummyDomain.toDto(): DummyDto =
    DummyDto(
        id = this.id,
        name = name,
    )

// toEntity() 함수

fun DummyDto.toEntity(): DummyEntity =
    DummyEntity(
        id = this.id,
        name = name,
    )
