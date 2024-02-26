package org.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StatusController {
    @GetMapping(value = "/api/v1/users")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(new User("some_login", "some_password"));
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> userData(@RequestBody User user) {
        if (user == null || StringUtils.isBlank(user.getLogin()) ||
                StringUtils.isBlank(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid input data");
        }
        user.setCurrentTime(java.time.LocalDate.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return ResponseEntity.ok(user);
    }
}



