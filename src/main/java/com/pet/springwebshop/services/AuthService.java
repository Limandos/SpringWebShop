package com.pet.springwebshop.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pet.springwebshop.dto.auth.RegisterDTO;
import com.pet.springwebshop.entity.Customer;
import com.pet.springwebshop.entity.Refresh;
import com.pet.springwebshop.entity.Role;
import com.pet.springwebshop.exception.NotFoundException;
import com.pet.springwebshop.repo.CustomerRepo;
import com.pet.springwebshop.repo.RefreshRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class AuthService {
    private final CustomerRepo customerRepo;
    private final RefreshRepo refreshRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapService mapService;

    @Autowired
    public AuthService(CustomerRepo customerRepo, RefreshRepo refreshRepo, BCryptPasswordEncoder bCryptPasswordEncoder, MapService mapService) {
        this.customerRepo = customerRepo;
        this.refreshRepo = refreshRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapService = mapService;
    }

    public RegisterDTO register(RegisterDTO registerDTO) {
        if (customerRepo.findCustomerByEmail(registerDTO.getEmail()).isPresent())
            throw new DuplicateKeyException(("Customer by email " + registerDTO.getEmail() + " is already registered."));
        Customer customer = new Customer();
        customer.setName(registerDTO.getName());
        customer.setEmail(registerDTO.getEmail());
        customer.setRole(Role.USER.name());
        customer.setPasswordHash(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        customerRepo.save(customer);
        return registerDTO;
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String requestRefreshToken = WebUtils.getCookie(request, "RefreshToken").getValue();
        if (authorizationHeader != null && requestRefreshToken != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String accessToken = authorizationHeader.split(" ")[1];
                DecodedJWT decodedJWT = JWT.decode(accessToken);
                String email = decodedJWT.getSubject();
                List<Refresh> refreshTokens = refreshRepo.findAllRefreshByCustomer(customerRepo.findCustomerByEmail(email).get().getId());
                Refresh refresh = refreshTokens.stream()
                        .filter(t -> t.getToken().equals(requestRefreshToken))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Refresh token wasn't found."));
                if (!refresh.isUsed() && refresh.getExpireDate().isBefore(LocalDateTime.now())) {
                    refresh.setUsed(true);
                } else {
                    response.sendError(FORBIDDEN.value());
                }
            } catch (JWTDecodeException e) {
                response.sendError(FORBIDDEN.value());
            }
        } else {
            response.sendError(FORBIDDEN.value());
        }
    }
}