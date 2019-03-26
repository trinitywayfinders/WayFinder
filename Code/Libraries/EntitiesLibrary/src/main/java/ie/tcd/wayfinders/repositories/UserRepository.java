package ie.tcd.wayfinders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tcd.wayfinders.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}