package com.techelevator.controller;
import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Photo;
import com.techelevator.model.PhotoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/photos")
public class PhotoController {
   TestController testController;
    private final PhotoDAO photoDAO;
    private final UserDAO userDAO;

    public PhotoController(PhotoDAO photoDAO, UserDAO userDAO) {
        this.photoDAO = photoDAO;
        this.userDAO = userDAO;
    }


    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public List<Photo> returnPhotoByUserId(@PathVariable("id") int user_id ){
        List<Photo> allUsersPhotos = photoDAO.getPhotoByUserId(user_id);
        return allUsersPhotos;

    }

    @GetMapping("")
    public List<Photo> returnALLPhotoS(){
        List<Photo> allUsersPhotos = photoDAO.getAllPhotos();
        return allUsersPhotos;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<String> createPhoto(@Valid @RequestBody PhotoRequest getPhotoData) {
        if(photoDAO.create(getPhotoData.getUserId(), getPhotoData.getFileName(), getPhotoData.getLink(), getPhotoData.getCaption())) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("err", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/uploadCredentials")
    public String getCredentialUpload(@RequestParam String keyName, @RequestParam String contentType) {
        S3Presigner presigner = S3Presigner.create();
        try {
            PutObjectRequest putObjectRequest =
                    PutObjectRequest.builder()
                            .bucket("techelevatorgrambucket")
                            .key(keyName)
                            .contentType(contentType)
                            .build();
            PutObjectPresignRequest putObjectPresignRequest =
                    PutObjectPresignRequest.builder()
                            .signatureDuration(Duration.ofMinutes(15))
                            .putObjectRequest(putObjectRequest)
                            .build();
            // Generate the presigned request
            PresignedPutObjectRequest presignedPutObjectRequest =
                    presigner.presignPutObject(putObjectPresignRequest);
            // Log the presigned URL
            System.out.println("Presigned URL: " + presignedPutObjectRequest.url());
            return "" + presignedPutObjectRequest.url();
        } catch (S3Exception e) {
            e.getStackTrace();
        }
    return null;
    }
}