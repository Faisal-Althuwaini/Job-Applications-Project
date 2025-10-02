package com.wakeb.jobsapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.wakeb.jobsapplication.dto.UpdateRoleRequest;
import com.wakeb.jobsapplication.dto.UserDTO;
import com.wakeb.jobsapplication.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping("{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {

        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @PutMapping("{email}/role")
    public ResponseEntity<UserDTO> updateUserRole(
            @PathVariable String email,
            @RequestBody UpdateRoleRequest updateRoleRequest) {
        UserDTO updatedUser = userService.updateUserRole(email, updateRoleRequest.getRole());
        return ResponseEntity.ok(updatedUser);
    }
}