
package com.reddy.smartatm.service;

import com.reddy.smartatm.entity.User;

public interface UserService {

    User login(String username, String password);

    User save(User user);

    User findByUsername(String username);
    void changePassword(User user,
                    String currentPassword,
                    String newPassword);
    User updateProfile(User user, String email, String phone);
    User findByUsernameAndEmail(String username, String email);

void resetPassword(User user, String newPassword);

}
