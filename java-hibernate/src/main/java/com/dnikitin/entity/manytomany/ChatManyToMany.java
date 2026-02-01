package com.dnikitin.entity.manytomany;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "chat_many_to_many")
public class ChatManyToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "chats")
    //fetchTpe = Lazy by default. Betterto no use CascadeType.all because we don't wont to delete chat when delete user from DB
    private List<UserManyToMany> users = new ArrayList<>();
}
