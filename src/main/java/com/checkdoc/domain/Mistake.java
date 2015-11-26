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
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MISTAKE_TYPE_ID")
    private MistakeType mistakeType;

    public Mistake(String text, Document document, MistakeType mistakeType) {
        this.text = text;
        this.document = document;
        this.mistakeType = mistakeType;
    }

    public Mistake() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mistake)) return false;

        Mistake mistake = (Mistake) o;

        if (!getId().equals(mistake.getId())) return false;
        if (!text.equals(mistake.text)) return false;
        if (!getDocument().equals(mistake.getDocument())) return false;
        return getMistakeType().equals(mistake.getMistakeType());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + getDocument().hashCode();
        result = 31 * result + getMistakeType().hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public MistakeType getMistakeType() {
        return mistakeType;
    }

    public void setMistakeType(MistakeType mistakeType) {
        this.mistakeType = mistakeType;
    }

    @Override
    public String toString() {
        return "Mistake{" +
                "text='" + text + '\'' +
                ", document=" + document +
                ", mistakeType=" + mistakeType +
                '}';
    }
}
