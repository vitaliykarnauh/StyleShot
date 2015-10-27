package com.checkdoc.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "MISTAKE_TYPE")
@Table(name = "MISTAKE_TYPE")
public class MistakeType {

	@Id
	@GeneratedValue(generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
	@Column(name = "MISTAKE_TYPE_ID", unique = true, nullable = false)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DEFINITION")
	private String definition;

	@OneToMany(mappedBy = "mistakeType")
	private Set<Mistake> mistakes = new HashSet<>();

	public MistakeType() {
	}

	public MistakeType(String name, String definition) {
		this.name = name;
		this.definition = definition;
	}

	public MistakeType(String name, String definition, Set<Mistake> mistakes) {
		this.name = name;
		this.definition = definition;
		this.mistakes = mistakes;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public Set<Mistake> getMistakes() {
		return mistakes;
	}

	public void setMistakes(Set<Mistake> mistakes) {
		this.mistakes = mistakes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MistakeType that = (MistakeType) o;

		if (getId() != that.getId()) return false;
		if (!getName().equals(that.getName())) return false;
		return !(getDefinition() != null ? !getDefinition().equals(that.getDefinition()) : that.getDefinition() != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (getId() ^ (getId() >>> 32));
		result = 31 * result + getName().hashCode();
		result = 31 * result + (getDefinition() != null ? getDefinition().hashCode() : 0);
		return result;
	}
}
