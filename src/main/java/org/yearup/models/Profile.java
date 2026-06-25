package org.yearup.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile
{
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    @Column(name = "phone")
    private String phone = "";

    @Column(name = "email")
    private String email = "";

    @Column(name = "address")
    private String address = "";

    @Column(name = "city")
    private String city = "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "zip")
    private String zip = "";

    public Profile(int id) {
        this.userId = id;
    }
}