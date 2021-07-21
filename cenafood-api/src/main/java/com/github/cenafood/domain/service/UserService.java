package com.github.cenafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.BusinessException;
import com.github.cenafood.domain.exception.EntityInUseException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Role;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.repository.UserRepository;

/**
 * @author elielcena
 *
 */
@Service
public class UserService {

    private static final String MSG_RESOURCE_NOT_FOUND = "There is no user register with code %d";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
    }

    public User save(User user) {
        Optional<User> existUser = userRepository.findByEmail(user.getEmail());

        if (existUser.isPresent() && !existUser.get().equals(user))
            throw new BusinessException("User already exists");

        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    public void changePassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
        User user = findById(id);

        validCurrentpassword(user, currentPassword);
        validNewPasswords(newPassword, confirmPassword);

        user.setPassword(newPassword);

        save(user);
    }

    public void delete(Long id) {
        try {
            userRepository.delete(findById(id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

    public void addRoleToUser(Long idUser, Long idRole) {
        addOrRemoveRole(Boolean.TRUE, idUser, idRole);
    }

    public void removeRoleToUser(Long idUser, Long idRole) {
        addOrRemoveRole(Boolean.FALSE, idUser, idRole);
    }

    private void validCurrentpassword(User user, String currentPassword) {
        if (!passwordEncoder.matches(currentPassword, user.getPassword()))
            throw new BusinessException("The current password is not valid");
    }

    private void validNewPasswords(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword))
            throw new BusinessException("New passwords do not match");
    }

    private void addOrRemoveRole(Boolean isAdd, Long idUser, Long idRole) {
        User user = findById(idUser);
        Role role = roleService.findById(idRole);

        user.addOrRemoveRole(isAdd, role);

        save(user);
    }

}
