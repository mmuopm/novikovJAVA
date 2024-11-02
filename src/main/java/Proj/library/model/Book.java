package Proj.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "book_sequence", allocationSize = 1)
public class Book  extends GenericModel{

    @Column(name = "title", nullable = false)
    private String bookTitle;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "amount")
    private Integer amount;


    @Column(name = "storage_palace", nullable = false)
    private String storagePlace;

    @Column(name = "online_copy_path")
    private String onlineCopyPath;

    @Column(name = "genre", nullable = false)
    @Enumerated(value = EnumType.STRING) //чтоб enum не выводился в числовом значении
    private Genre genre;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_authors", //чтобы таблицы books и authors стали равнозначными
    joinColumns = @JoinColumn(name = "book_id"), foreignKey = @ForeignKey(name = "FK_BOOKS_AUTHORS"),
    inverseJoinColumns = @JoinColumn(name = "author_id"), inverseForeignKey = @ForeignKey(name = "FK_AUTHORS_BOOKS"))
    List<Author> authors;
}
