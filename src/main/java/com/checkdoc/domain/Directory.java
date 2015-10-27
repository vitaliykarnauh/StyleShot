package com.checkdoc.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "DIRECTORY")
@Table(name = "DIRECTORY")
public class Directory {
	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "DIRECTORY_ID")
	private long id;

	@Column(name = "URL")
	private String url;

	@Column(name = "DATE")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "directory")
	private Set<Document> documents = new HashSet<>();

	public Directory() {
	}

	public Directory(String url, Date date, User user) {
		this.url = url;
		this.date = date;
		this.user = user;
	}


	public long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String name) {
		this.url = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Directory directory = (Directory) o;

		if (getId() != directory.getId()) return false;
		if (!getUrl().equals(directory.getUrl())) return false;
		if (!getDate().equals(directory.getDate())) return false;
		if (!getUser().equals(directory.getUser())) return false;
		return !(getDocuments() != null ? !getDocuments().equals(directory.getDocuments()) : directory.getDocuments() != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (getId() ^ (getId() >>> 32));
		result = 31 * result + getUrl().hashCode();
		result = 31 * result + getDate().hashCode();
		result = 31 * result + getUser().hashCode();
		result = 31 * result + (getDocuments() != null ? getDocuments().hashCode() : 0);
		return result;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
