package com.bm.todo.model

import javax.persistence.*

@Entity
@Table(name = "todo")
data class Todo(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Int,

                @Column(nullable = false)
                var task: String,

                @Column(nullable = false)
                var status: Boolean = false)