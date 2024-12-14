package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<User> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getUsers(page, size);
    }

//    @GetMapping
//    @ResponseBody
//    public Resource getUserPhoto(@PathVariable String  photoName) {
//        Path imagePath = Paths.get("photos/" + photoName + ".jpg"); // Путь к изображению пользователя
//        try {
//            return (Resource) new UrlResource(imagePath.toUri());
//        } catch (Exception e) {
//            throw new RuntimeException("Ошибка при получении изображения", e);
//        }
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam String name, @RequestParam MultipartFile file, @RequestParam String city, @RequestParam String photoName) throws IOException {
        User user = userService.saveUserWithPhoto(name, file, city, photoName);
        return ResponseEntity.ok("User uploaded successfully with photo ID: " + user.getId());
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/filter-search")
    public String filterAndSearch(@RequestParam(required = false) String photoName, Model model) {
        List<User> users = userService.searchTasksByPhotoName(photoName);
        model.addAttribute("users", users);
        model.addAttribute("photoName", photoName);
        return "user-search";
    }
}

















































//    //8 filtraton
//    @GetMapping("/filter")
//    public String filterTasks(@RequestParam(required = false) String photoName, Model model) {
//        List<User> users = userService.searchTasksByPhotoName(photoName);
//        model.addAttribute("users", users);
//        return "user-search";
//    }