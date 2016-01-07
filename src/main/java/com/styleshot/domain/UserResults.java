package com.styleshot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "USER_RESULTS")
@Table(name = "USER_RESULTS")
public class UserResults {


    public UserResults() {
    }


    public UserResults(String description, String resultLinks, User user) {
        this.description = description;
        this.resultLinks = resultLinks;
        this.user = user;
    }

    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "RESULT_ID", unique = true, nullable = false)
    private Long resultId;


    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "RESULT_LINKS")
    @Lob
    private String resultLinks;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = true)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "LINK_ID")
    private UserLinks userLinks;


    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResultLinks() {
        return resultLinks;
    }

    public void setResultLinks(String resultLinks) {
        this.resultLinks = resultLinks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLinks getUserLinks() {
        return userLinks;
    }

    public void setUserLinks(UserLinks userLinks) {
        this.userLinks = userLinks;
    }
}
