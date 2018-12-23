package fr.laerce.cinema.model;//package fr.laerce.cinema.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayPk implements Serializable {

    @Column(name = "person_id")
    private Long personPlay;
    @Column(name = "film_id")
    private Long filmPlay;

    public PlayPk() {
    }

    public PlayPk(Long person_id, Long film_id) {
        this.personPlay = person_id;
        this.filmPlay = film_id;
    }


    public Long getPerson_id() {
        return personPlay;
    }
    public void setPerson_id(Long person_id) {
        this.personPlay = person_id;
    }

    public Long getFilm_id() {
        return filmPlay;
    }

    public void setFilm_id(Long film_id) {
        this.filmPlay= film_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayPk)) return false;
        PlayPk playPk = (PlayPk) o;
        return Objects.equals (personPlay, playPk.personPlay) &&
                Objects.equals (filmPlay, playPk.filmPlay);
    }

    @Override
    public int hashCode() {
        return Objects.hash (personPlay, filmPlay);
    }
}
