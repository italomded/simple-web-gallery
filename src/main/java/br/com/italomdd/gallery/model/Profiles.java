package br.com.italomdd.gallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity(name="profiles")
public class Profiles implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String authority;
	
	@Override
	public String getAuthority() {
		return this.authority;
	}
	
	public Long getId() {
		return this.id;
	}

}
