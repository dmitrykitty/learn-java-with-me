package com.dnikitin.entity.manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "chats")
@EqualsAndHashCode(of = "nickname")
@Builder
@Entity
@Table(name = "user_many_to_many")
public class UserManyToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Integer age;

    @Builder.Default
    @ManyToMany
    //owner(main table)
    @JoinTable(
            name = "user_chat_many_to_many",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<ChatManyToMany> chats = new ArrayList<>();

    public void addChat(ChatManyToMany chat) {
        chats.add(chat);
        chat.getUsers().add(this);
    }
}
