/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import org.openrqm.mapper.UserDetailsRowMapper;
import org.openrqm.model.RQMToken;
import org.openrqm.model.RQMUser;
import org.openrqm.model.RQMUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
    public ResponseEntity<Void> changeUser(@ApiParam(value = "", required=true) @Valid @RequestBody RQMUser user, @ApiParam(value = "", required=true) @RequestHeader(value="id", required=true) Long id, @ApiParam(value = "", required=true) @RequestHeader(value = "passwordHash", required=true) String passwordHash, @ApiParam(value = "The SHA512 of the new password", required=true) @RequestHeader(value = "newPasswordHash", required=true) String newPasswordHash) {
        try {
            // check again whether password matches before deletion
            RQMUserDetails userInDatabase = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?;", new Object[]{ id }, new UserDetailsRowMapper());
            if (!passwordEncoder.matches(passwordHash, userInDatabase.getPasswordHash())) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            // if new password hash is not empty generate a salted hash with bcrypt
            String bcryptPasswordHash = userInDatabase.getPasswordHash();
            if (!newPasswordHash.isEmpty()) {
                // base64 decode provided password hash
                byte[] providedPasswordHashBytes = Base64.getDecoder().decode(passwordHash);
                String providedPasswordHash = new String(providedPasswordHashBytes);
                // hash and salt provided password hash with the use of bcrypt
                bcryptPasswordHash = passwordEncoder.encode(providedPasswordHash);
            }
            jdbcTemplate.update("UPDATE user SET email = ?, name = ?, surname = ?, department = ?, password_hash = ? WHERE id = ?;",
                    user.getEmail(), user.getName(), user.getSurname(), user.getDepartment(), bcryptPasswordHash, id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "", required=true) @RequestHeader(value = "passwordHash", required=true) String passwordHash, @ApiParam(value = "", required=true) @RequestHeader(value="id", required=true) Long id) {
        try {
            // check again whether password matches before deletion
            RQMUserDetails userInDatabase = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?;", new Object[]{ id }, new UserDetailsRowMapper());
            if (!passwordEncoder.matches(passwordHash, userInDatabase.getPasswordHash())) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            jdbcTemplate.update("DELETE FROM user WHERE id = ?;", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMToken> login(@ApiParam(value = "", required=true) @RequestHeader(value = "passwordHash", required=true) String passwordHash, @ApiParam(value = "", required=true) @RequestHeader(value = "email", required=true) String email) {
        try {
            // get stored password hash from user in database
            RQMUserDetails userInDatabase = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?;", new Object[]{ email }, new UserDetailsRowMapper());
            // return token if password hashes match
            if (passwordEncoder.matches(passwordHash, userInDatabase.getPasswordHash())) {
                // generate authentication token
                byte[] randomBytes = new byte[24];
                secureRandom.nextBytes(randomBytes);
                String randomToken = base64UrlEncoder.encodeToString(randomBytes);
                RQMToken token = new RQMToken();
                token.setId(userInDatabase.getId());
                token.setToken(randomToken);
                // store generated authentication token for user
                jdbcTemplate.update("UPDATE user SET token = ? WHERE email = ?;", randomToken, email);
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> logout(@ApiParam(value = "", required=true) @RequestHeader(value = "id", required=true) Long id) {
        try {
            jdbcTemplate.update("UPDATE user SET token = ? WHERE id = ?;", null, id);
            //TODO check whether user exists. don't give the user any info
            //on whether a users exists by logging an id out
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMToken> register(@ApiParam(value = "", required=true) @Valid @RequestBody RQMUser user, @ApiParam(value = "", required=true) @RequestHeader(value = "passwordHash", required=true) String passwordHash) {
        // hash and salt provided password hash with the use of bcrypt
        String bcryptPasswordHash = passwordEncoder.encode(passwordHash);
        // generate authentication token
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String randomToken = base64UrlEncoder.encodeToString(randomBytes);
        RQMToken token = new RQMToken();
        token.setToken(randomToken);
        try {
            jdbcTemplate.update("INSERT INTO user(id, email, name, surname, department, password_hash, token) VALUES (?, ?, ?, ?, ?, ?, ?);",
                    0, user.getEmail(), user.getName(), user.getSurname(), user.getDepartment(), bcryptPasswordHash, randomToken);
            RQMUserDetails userInDatabase = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email = ?;", new Object[]{ user.getEmail() }, new UserDetailsRowMapper());
            token.setId(userInDatabase.getId());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
