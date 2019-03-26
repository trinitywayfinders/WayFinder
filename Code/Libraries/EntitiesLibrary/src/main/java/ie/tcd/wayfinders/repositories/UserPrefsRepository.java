package ie.tcd.wayfinders.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ie.tcd.wayfinders.entities.UserPrefs;

public interface UserPrefsRepository extends CrudRepository<UserPrefs, Long> {
}