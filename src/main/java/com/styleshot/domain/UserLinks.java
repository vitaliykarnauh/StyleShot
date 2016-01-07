package com.styleshot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "USER_LINKS")
public class UserLinks {

    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "LINK_ID", unique = true, nullable = false)
    private Long linkId;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",  nullable = true)
    @JsonIgnore
    private User user;

    @Column(name = "FILE_NAME")
    @Lob
    private String fileName;

    @Column(name = "VIEWED")
    private boolean isViewed;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userLinks", cascade = CascadeType.ALL)
    @JsonIgnore
    public UserResults userResults;


    public UserLinks() {
    }

    public UserLinks(User user, String fileName, boolean isViewed) {
        this.user = user;
        this.fileName = fileName;
        this.isViewed = isViewed;
    }

    public Long getLinkId() {
        return linkId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean isViewed) {
        this.isViewed = isViewed;
    }

    public UserResults getUserResults() {
        return userResults;
    }

    public void setUserResults(UserResults userResults) {
        this.userResults = userResults;
    }
}
