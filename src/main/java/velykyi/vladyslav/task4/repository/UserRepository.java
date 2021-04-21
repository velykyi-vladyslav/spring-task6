package velykyi.vladyslav.task4.repository;

import velykyi.vladyslav.task4.model.User;

public interface UserRepository {
    User getUser(String email);

    User createUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);
}
