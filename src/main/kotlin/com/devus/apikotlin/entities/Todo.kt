package com.devus.apikotlin.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "todos")
class Todo (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @field:NotNull
    var title: String,

    @field:NotNull
    var description: String
)