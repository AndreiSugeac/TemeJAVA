package ro.unibuc.springlab8example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/student")
    public ResponseEntity<UserDto> createStudent(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.STUDENT));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> get(@PathVariable String username) {
        return ResponseEntity
                .ok()
                .body(userService.getOne(username));
    }

    @PutMapping("/student/{username}")
    public ResponseEntity<UserDto> updateStudent(@PathVariable String username, @RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.updateOne(username, userDto));
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(userService.deleteOne(id));
    }

    @GetMapping("/users/{type}")
    public ResponseEntity<List<UserDto>> getUsersByType(@PathVariable String type) {
        return ResponseEntity
                .ok()
                .body(userService.getUsersByType(UserType.valueOf(type)));
    }
    // TODO: homework: endpoints for updating a user, deleting one, get all users filtered by tupe
}
