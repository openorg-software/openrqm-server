/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.openrqm.mapper.UserRowMapper;
import org.openrqm.model.RQMUser;
import org.openrqm.model.RQMWorkspaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserApiController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64UrlEncoder = Base64.getUrlEncoder();

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<RQMWorkspaces> changeUser(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "passwordHash", required = true) String passwordHash, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "surname", required = true) String surname, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "department", required = true) String department, @NotNull @ApiParam(value = "The SHA512 of the new password", required = true) @Valid @RequestParam(value = "newPasswordHash", required = true) String newPasswordHash) {
        // check again whether password matches before deletion
        RQMUser user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?;", new Object[]{ email }, new UserRowMapper());
        if (!passwordEncoder.matches(passwordHash, user.getPasswordHash())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            jdbcTemplate.update("UPDATE user SET email = ?, name = ?, surname = ?, department = ?, password_hash = ? WHERE email = ?;",
                    email, name, surname, department, passwordHash, email);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "passwordHash", required = true) String passwordHash, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        // check again whether password matches before deletion
        RQMUser user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?;", new Object[]{ email }, new UserRowMapper());
        if (!passwordEncoder.matches(passwordHash, user.getPasswordHash())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            jdbcTemplate.update("DELETE FROM user WHERE email = ?;", email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> login(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "passwordHash", required = true) String passwordHash, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        try {
            // get password_hash from database
            RQMUser user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?;", new Object[]{ email }, new UserRowMapper());
            if (passwordEncoder.matches(passwordHash, user.getPasswordHash())) {
                // generate authentication token
                byte[] randomBytes = new byte[24];
                secureRandom.nextBytes(randomBytes);
                String token = base64UrlEncoder.encodeToString(randomBytes);
                // store generated authentication token for user
                jdbcTemplate.update("UPDATE user SET token = ? WHERE email = ?;", token, email);
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> logout(@ApiParam(value = "The token of the user that should be logged out", required=true) @Valid @RequestBody String token) {
        try {
            jdbcTemplate.update("UPDATE user SET token = ? WHERE token = ?;", null, token);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> register(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "passwordHash", required = true) String passwordHash, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "email", required = true) String email, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "name", required = true) String name, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "surname", required = true) String surname, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "department", required = true) String department) {
        // base64 decode provided password hash
        byte[] providedPasswordHashBytes = Base64.getDecoder().decode(passwordHash);
        String providedPasswordHash = new String(providedPasswordHashBytes);
        // hash and salt provided password hash with the use of bcrypt
        String bcryptPasswordHash = passwordEncoder.encode(providedPasswordHash);
        // generate authentication token
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64UrlEncoder.encodeToString(randomBytes);
        try {
            jdbcTemplate.update("INSERT INTO user(id, email, name, surname, department, password_hash, token) VALUES (?, ?, ?, ?, ?, ?, ?);",
                    0, email, name, surname, department, bcryptPasswordHash, token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
