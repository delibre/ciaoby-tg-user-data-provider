package by.ciao.controller;

import by.ciao.service.UserService;
import by.ciao.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<UserDto> getUserByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(userService.getUserByChatId(chatId));
    }

    @GetMapping("/last-hour")
    public ResponseEntity<List<UserDto>> getUsersForTheLastHour() {
        return ResponseEntity.ok(userService.getUsersForTheLastHour());
    }

    @GetMapping("/last-day")
    public ResponseEntity<List<UserDto>> getUsersForTheLastDay() {
        return ResponseEntity.ok(userService.getUsersForTheLastDay());
    }

    @PutMapping("/update-contactinfo/{chatId}")
    public ResponseEntity<UserDto> updateContactInfo(@PathVariable String chatId, @RequestBody UserDto updateUser) {
        return ResponseEntity.ok(userService.updateContactInfo(chatId, updateUser));
    }

    @PutMapping("/update-testinfo/{chatId}")
    public ResponseEntity<UserDto> updateTestInfo(@PathVariable String chatId, @RequestBody UserDto updateUser) {
        return ResponseEntity.ok(userService.updateTestInfo(chatId, updateUser));
    }

}
