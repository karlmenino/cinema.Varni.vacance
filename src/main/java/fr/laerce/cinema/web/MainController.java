package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonsDao;
import fr.laerce.cinema.dao.RoleDao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController {
    //on peut utiliser cette méthode avec autowired et component dans le servlet DataModel
    @Autowired
    PersonsDao personsDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    FilmDao filmDao;


    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M){
        M.addAttribute ("films",filmDao.findAll ());
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }

}
