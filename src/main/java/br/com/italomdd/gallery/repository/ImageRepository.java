package br.com.italomdd.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.italomdd.gallery.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	
	public List<Image> findByUserUsername(String username);
	
	@Query("SELECT i FROM Image i JOIN i.user")
	public List<Image> findWithAll();
	
}
