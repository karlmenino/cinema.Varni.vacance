package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="films")
public class Film {
    private long id;
    private String title;
    private Double rating;
    private String image_path;
    private String summary;
    private Personne film_director;
    private List<Role> posts;
    private List<Genre> genreFilm = new ArrayList<Genre> ();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "film")
    public List<Role> getPosts() {
        return posts;
    }

    public void setPosts(List<Role> posts) {
        this.posts = posts;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 120)
    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }


    @Basic
    @Column(name = "summary", nullable = true, length = 80)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @ManyToOne
    @JoinColumn(name = "film_director")
    public Personne getFilm_director() {
        return film_director;
    }

    public void setFilm_director(Personne film_director) {
        this.film_director = film_director;
    }

    @ManyToMany(mappedBy="listFilm")
    public List<Genre> getGenreFilm() {return genreFilm;}

    public void setGenreFilm(List<Genre> genreFilm) {this.genreFilm = genreFilm;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash (getId ());
    }

}
