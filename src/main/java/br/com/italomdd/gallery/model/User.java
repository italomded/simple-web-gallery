package br.com.italomdd.gallery.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String password;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Image> images = new ArrayList<Image>();
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Image> favoriteImages = new ArrayList<Image>();
	@OneToMany(fetch = FetchType.EAGER)
	List<Profiles> profiles = new ArrayList<>();

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public List<Image> getImages() {
		return images;
	}
	public List<Image> getFavoriteImages() {
		return favoriteImages;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return profiles;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
