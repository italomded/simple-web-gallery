package br.com.italomdd.gallery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.italomdd.gallery.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	public List<Image> findByUserUsername(String username);
	@Query("SELECT i FROM Image i JOIN i.user")
	public List<Image> findWithAll();
	@Query("SELECT i FROM Image i JOIN i.user u WHERE u.username != ?1")
	public List<Image> findAllImagesExceptUser(String username);
	@Query("SELECT i FROM Image i WHERE i.id = ?1 AND i.user.username != ?2")
	public Optional<Image> findByIdAndUserUsername(Long id, String username);
}
