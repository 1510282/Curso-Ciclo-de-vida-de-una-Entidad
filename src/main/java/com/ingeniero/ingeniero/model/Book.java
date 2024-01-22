package com.ingeniero.ingeniero.model;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double price;
//para cambiar el nombre de la columna
    @Column ( name = "num_pages")
    private Integer numPages;

    private Boolean published;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;


@ManyToMany
@JoinTable(
        name = "book_category",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
private Set<Category> categories = new HashSet<>();


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public Book() {
    }

    public Book(String title, Double price, Integer numPages, Boolean published, Author author) {
        this.title = title;
        this.price = price;
        this.numPages = numPages;
        this.published = published;
        this.author = author;
    }
public Author getAuthor(){
        return author;

}
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title +
                ", price=" + price +
                ", numPages=" + numPages +
                ", published=" + published +
                '}';
    }


}
