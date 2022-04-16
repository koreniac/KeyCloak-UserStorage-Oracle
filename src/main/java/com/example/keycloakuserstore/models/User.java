package com.example.keycloakuserstore.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@NamedQueries({
        @NamedQuery(name="getUserByUsername", query="select u from User u where LOWER(u.username) LIKE :username"),
        // @NamedQuery(name="getUserByUsername", query="select u from User u where u.username LIKE :username"),

        // @NamedQuery(name="getUserByUsername", query="select u from User u where u.username = :username"),
        @NamedQuery(name="getUserByEmail", query="select u from User u where u.email = :email"),
        @NamedQuery(name="getUserCount", query="select count(u) from User u"),
        @NamedQuery(name="getAllUsers", query="select u from User u"),
        @NamedQuery(name="searchForUser", query="select u from User u where " +
                "( lower(u.username) like :search or u.email like :search ) order by u.username"),
})
@Entity
@Table(name = "SRP_USER")
@Data
@Accessors(chain = true)
// @Access(value=AccessType.FIELD)
// @Access(value=AccessType.PROPERTY)
public class User {
    // @Id
    // @Column(name = "USER_ID", unique = true, nullable = false)
    // // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    // // @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    // private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID",unique=true, nullable = false)
    private String id;

    @Column(unique = true,name="USER_LOGIN")
    private String username;
    @Column(unique = true,name="USER_EMAIL")
    private String email;
    @Column(unique = true,name="USER_PASSWORD")
    private String password;
    @Column(name="USER_TEL")
    private String phone;
    @Column(name="USER_POSITION")
    private String position;
    @Column(name="STATUS")
    private String status;
    @Column(name="USER_TYPE")
    private String type;
    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = UUID.randomUUID().toString();
    // }
    // @Column(unique = true,name="USER_ID")
	// private String user_id;

    // @Column(name="USER_REQUEST_ID")
	// private String user_request_id;

    // @Column(name="USER_ID")
	// private String user_id;

	// @Column(name="USER_TYPE")
	// private int type;

	// @Column(name="USER_NAME")
	// private String username;

    // @Column(name="USER_PASSWORD")
    // private String password;

    // @Column(name="USER_POSITION")
    // private String position;

    // @Column(name="USER_DEPT")
    // private String dept;

    // @Column(name="USER_DIVISION")
    // private String division;

    // @Column(name="USER_TEL")
    // private String phone;

    // @Column(name="USER_MOBILE")
    // private String mobile;

    // @Column(name="USER_EMAIL")
    // private String email;

    // @Column(name="CREATOR")
    // private String creator;

    // @Column(name="CREATOR_DATE")
    // private Date creator_date;

    // @Column(name="LAST_MODIFIED_USER")
    // private String last_mod_user;

    // @Column(name="LAST_MODIFIED_DATE")
    // private Date last_mod_date;

    // @Column(name="STATUS")
    // private String status;

	// private Date birthDate;

	// private String city;

	// private String province;

	// private String address;

	// @Column(name="password")
	// private String password;
}
