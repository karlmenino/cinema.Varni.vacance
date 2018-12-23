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
        //on ajoute a l'objet model la clef nom et karl
        M.addAttribute ("nom","karl" );
        M.addAttribute ("films",filmDao.findAll ());
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }

    @GetMapping("/film/{id}")
    //on recupere id grace à pathvariable
    public String detail(Model m, @PathVariable("id") Long id){
        m.addAttribute ("film",filmDao.findById (id).get ());
        m.addAttribute ("role", filmDao.findById (id).get ().getPosts ());
        return"detail";
    }

    @Value( "${url}" )
    private String url;
    //deuxieme methode pour affichezr  image
    @GetMapping("/affiche/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity (HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url+id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache ().getHeaderValue ());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
       return null;
 }
 //on copie/colle la methode pour le portrait des acteur
    @Value( "${url2}" )
    private String url2;
    //que l'on mappe sur image/id id etant le nom brut de l'image
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity2 (HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url2+id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache().getHeaderValue());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }
    @GetMapping("/acteur/{id}")
    //on recupere id grace à pathvariable
    public String acteur(Model m, @PathVariable("id") String id){
        //on envoie a acteur la personne concernée grace a la methode getbyaf et id qui est le nom de l'image
        m.addAttribute ("actor", personsDao.findByPhotoPath (id));
        return"acteur";}
}
