package com.styleshot.domain;


import javax.persistence.*;

@Entity(name = "USER_LINKS")
@Table(name = "USER_LINKS")
public class UserLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LINK_ID", unique = true, nullable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    private Long userId;

    @Column(name = "LINK")
    @Lob
    private String link;

    @Column(name = "VIEWED")
    private boolean isViewed;


    public UserLinks() {
    }


    public UserLinks(Long userId, String link, boolean isViewed) {
        this.userId = userId;
        this.link = link;
        this.isViewed = isViewed;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean isViewed) {
        this.isViewed = isViewed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLinks userLinks = (UserLinks) o;

        if (isViewed != userLinks.isViewed) return false;
        if (id != null ? !id.equals(userLinks.id) : userLinks.id != null) return false;
        if (link != null ? !link.equals(userLinks.link) : userLinks.link != null) return false;
        if (userId != null ? !userId.equals(userLinks.userId) : userLinks.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (isViewed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserLinks{" +
                "id=" + id +
                ", userId=" + userId +
                ", link='" + link + '\'' +
                ", isViewed=" + isViewed +
                '}';
    }


}
