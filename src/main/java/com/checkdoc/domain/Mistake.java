package com.checkdoc.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "MISTAKE")
@Table(name = "MISTAKE")
public class Mistake {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "MISTAKE_ID", unique = true, nullable = false)
	private long id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "DOCUMENT_ID")
	private Document document;

	@ManyToOne
	@JoinColumn(name = "MISTAKE_TYPE_ID")
	private MistakeType mistakeType;

	public Mistake() {
	}

	public Mistake(String name) {
		this.name = name;
	}

	public Mistake(String name, Document document) {

		this.name = name;
		this.document = document;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Mistake mistake = (Mistake) o;

		if (getId() != mistake.getId()) return false;
		if (!getName().equals(mistake.getName())) return false;
		if (!getDocument().equals(mistake.getDocument())) return false;
		return mistakeType.equals(mistake.mistakeType);

	}

	@Override
	public int hashCode() {
		int result = (int) (getId() ^ (getId() >>> 32));
		result = 31 * result + getName().hashCode();
		result = 31 * result + getDocument().hashCode();
		result = 31 * result + mistakeType.hashCode();
		return result;
	}
}
