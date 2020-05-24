package com.aliyu.ism.user;

import com.aliyu.ism.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(
            @Validated @RequestBody User user,
            BindingResult validationResult
    ) {

        if (Utils.validationErrorCheck(validationResult))
            return ResponseEntity
                    .badRequest()
                    .build();

        switch (user.getId().substring(0, 3)) {
            case "VTR":
                user.setRole(UserRole.VOTER);
                break;
            case "ADM":
                user.setRole(UserRole.ADMIN);
                break;
            case "CDT":
                user.setRole(UserRole.CANDIDATE);
                break;
        }

        return ResponseEntity
                .ok(userRepository.save(user));
    }

    @DeleteMapping("{user}")
    public ResponseEntity<User> removeUser(@PathVariable User user) {
        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("change-picture/{username}")
    public ResponseEntity<String> uploadUserProfilePicture(
            @RequestParam("file") MultipartFile file,
            @PathVariable String username,
            ServletRequest servletRequest
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.indexOf('.'));

        String storageLocation =
                servletRequest
                        .getServletContext()
                        .getRealPath("/img/")
                        .concat(username)
                        .concat(extension);


        try {
            file.transferTo(Paths.get(storageLocation));
        } catch (IOException ex) {
            System.out.println("storing file failed");
            System.out.println(ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed");
        }
        return ResponseEntity.accepted().body("Uploaded Successful");
    }

    @GetMapping("generate-id/{role}")
    public String getGeneratedId(@PathVariable UserRole role) {
        switch (role) {
            case ADMIN:
                return UserIdGenerator.getAdminId();
            case CANDIDATE:
                return UserIdGenerator.getCandidate();
            default:
                return UserIdGenerator.getVoterId();
        }
    }

    @GetMapping("{id}")
    public User findUser(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // TODO CHANGE PASSWORD
    @PostMapping("password-reset/{id}")
    public ResponseEntity<String> resetPassword(
            @RequestParam String newPassword,
            @RequestParam String password,
            @PathVariable("id") User user
    ) {

        if (user == null) return ResponseEntity.badRequest().body("no user");
        else if (!user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("unauthorized");
        } else if (user.getPassword().equals(newPassword)) {
            return ResponseEntity.badRequest().body("Same old password used");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
        return ResponseEntity.ok("Password reset successful");
    }

    @PostMapping("logout/{id}")
    public ResponseEntity<User> logout(
            @PathVariable("id") User user,
            @RequestParam String password
    ) {
        if (!user.getPassword().equals(password))
            return ResponseEntity.badRequest().build();
        user.setAuthenticated(Boolean.FALSE);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("login/{id}")
    public ResponseEntity<User> login(
            @RequestParam("password") String password,
            @PathVariable("id") User user
    ) {
        if (user.getPassword().equals(password)) {
            user.setAuthenticated(Boolean.TRUE);
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("candidates")
    public ResponseEntity<List<User>> getCandidates() {
        return ResponseEntity
                .ok(userRepository.findByRole(UserRole.CANDIDATE));
    }


    @InitBinder

    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.addCustomFormatter(new UserRoleFormatter());
        dataBinder.addValidators(new UserValidator());
    }
}
