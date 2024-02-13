package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class StatusController {

    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, String>> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserData> userData(@RequestBody Credentials credentials) {
        String currentDate = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        credentials.setCurrentDate(currentDate);
        UserData userData = new UserData(credentials.getLogin(), credentials.getPassword(), credentials.getCurrentDate());
        return ResponseEntity.ok(userData);
    }
}



