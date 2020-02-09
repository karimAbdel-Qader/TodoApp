package com.bm.todo.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Int,

                @Column(nullable = false)
                var username: String,

                @Column(nullable = false)
                var password: String)