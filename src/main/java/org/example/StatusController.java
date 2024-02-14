package org.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (credentials == null || StringUtils.isBlank(credentials.getLogin()) ||
                StringUtils.isBlank(credentials.getPassword())) {
            throw new InvalidInputException("Invalid input data");
        }

        String currentDate = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        UserData userData = new UserData(credentials.getLogin(), credentials.getPassword(), currentDate);
        return ResponseEntity.ok(userData);
    }
}



