package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User saveUserWithPhoto(String name, MultipartFile file, String city, String photoName) throws IOException {
        User user = new User(name, file.getBytes(), city, photoName);
        return userRepository.save(user);
    }

    public List<User> searchUsersByPhotoName(String photoName) {
        return userRepository.findByPhotoNameLike(photoName);
    }

    public List<User> searchTasksByPhotoName(String photoName) {
        if (photoName == null || photoName.trim().isEmpty()) {
            return userRepository.findAll();  // Возвращаем всех пользователей, если параметр пустой
        }
        return userRepository.findByPhotoNameContainingIgnoreCase(photoName);  // Поиск по части названия
    }


    public List<User> filterUsersByPhotoName(String photoName) {
        return userRepository.findByPhotoName(photoName);
    }

}
