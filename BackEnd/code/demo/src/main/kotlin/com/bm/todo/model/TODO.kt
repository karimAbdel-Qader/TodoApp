package com.bm.todo.model

import javax.persistence.*

@Entity
@Table(name = "todo")
data class Todo(
                @Column(nullable = false)
                var taskName: String,

                @Column(nullable = false)
                var status: Boolean = false){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}