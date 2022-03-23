package br.com.italomdd.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u.favoriteImages FROM User u WHERE u.username = ?1")
	public List<Image> findByUsername(String username);
	
}
