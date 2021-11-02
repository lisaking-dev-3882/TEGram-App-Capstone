package com.techelevator.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.dao.CommentDAO;
import com.techelevator.dao.FavoriteDAO;
import com.techelevator.dao.LikeDAO;
import com.techelevator.dao.PhotoCommentDAO;
import com.techelevator.model.Comment;
import com.techelevator.model.Photo;
import com.techelevator.model.PhotoComment;
import com.techelevator.model.PhotoRequest;

@RestController
@CrossOrigin()
public class TestController {
    private PhotoDAO photoDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;
    private PhotoCommentDAO photoCommentDAO;
    private FavoriteDAO favoriteDAO;
    private LikeDAO likeDAO;
    
    public TestController(PhotoDAO photoDAO, UserDAO userDAO, CommentDAO commentDAO, 
    		PhotoCommentDAO photoCommentDAO, FavoriteDAO favoriteDAO, LikeDAO likeDAO) {
        this.photoDAO = photoDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
        this.photoCommentDAO = photoCommentDAO;
        this.favoriteDAO = favoriteDAO;
        this.likeDAO = likeDAO;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/testphotos", method = RequestMethod.POST)
    public boolean addPhoto(@Valid @RequestBody PhotoRequest request, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        boolean photoAdded = false;
        photoAdded = (boolean) photoDAO.create(userId, request.getFileName(), request.getLink(), request.getCaption());
        return photoAdded;
    }
    @RequestMapping(path = "/tenphotos", method = RequestMethod.GET)
    public List<Photo> getTen() {
        List<Photo> photos = new ArrayList<>();
        photos = photoDAO.findTen();
        return photos;
    }
    @RequestMapping(path = "/photofeed", method = RequestMethod.GET)
    public List<PhotoComment> getAllPhotosOneComment() {
        List<PhotoComment> photos = new ArrayList<>();
        photos = photoCommentDAO.allPhotosOneComment();
        return photos;
    }
    @RequestMapping(path = "/photo/{id}/comments", method = RequestMethod.GET)
    public List<Comment> getCommentsForPhoto(@PathVariable int id) {
        List<Comment> comments = new ArrayList<>();
        comments = commentDAO.findCommentsForPhoto(id);
        return comments;
    }
    @RequestMapping(path = "/photo/{id}", method = RequestMethod.GET)
    public Photo getPhotoById(@PathVariable int id) {
        Photo photo = photoDAO.getPhotoById(id);
        return photo;
    }
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public String getUserNameByUserId(@PathVariable int id) {
        String userName = userDAO.getUserById(id).getUsername();
        return userName;
    }
    @RequestMapping(path = "/favorites", method = RequestMethod.GET)
    public List<Photo> getFavorites(Principal principal) {
        List<Photo> photos = new ArrayList<>();
        int userId = userDAO.findIdByUsername(principal.getName());
        photos = photoDAO.getFavoritePhotos(userId);
        return photos;
    }
    @RequestMapping(path = "/user/{id}/favorites", method = RequestMethod.GET)
    public List<Photo> getFavorites(@PathVariable int id) {
        List<Photo> photos = new ArrayList<>();
        photos = photoDAO.getFavoritePhotos(id);
        return photos;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/addfavorite/{id}", method = RequestMethod.POST)
    public boolean addFavorite(@Valid @PathVariable int id, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        boolean favoriteAdded = false;
        favoriteAdded = favoriteDAO.create(userId, id);
        return favoriteAdded;
    }
    @RequestMapping(path = "/removefavorite/{id}", method = RequestMethod.DELETE)
    public boolean removeFavorite(@Valid @PathVariable int id, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        boolean favoriteRemoved = false;
        favoriteRemoved = favoriteDAO.delete(userId, id);
        return favoriteRemoved;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/like/{id}", method = RequestMethod.POST)
    public boolean addLike(@Valid @PathVariable int id, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        boolean likeAdded = false;
        if(likeDAO.create(userId, id) && photoDAO.like(id)) {
        	likeAdded = true;
        }
        return likeAdded;
    }
    @RequestMapping(path = "/unlike/{id}", method = RequestMethod.POST)
    public boolean removeLike(@Valid @PathVariable int id, Principal principal) {
        int userId = userDAO.findIdByUsername(principal.getName());
        boolean likeRemoved = false;
        if(likeDAO.delete(userId, id) && photoDAO.unlike(id)) {
        	likeRemoved = true;
        }
        return likeRemoved;
    }
    
    
}