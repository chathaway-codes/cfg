package models;

import java.util.ArrayList;
import java.util.List;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.mindrot.jbcrypt.BCrypt;

import scala.Some;
import securesocial.core.Identity;

@Entity
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    public Long id;
    
	public String email;
	
	public String username;
	
	public String first_name;
	
	public String last_name;
	
	public String full_name;
	
	public String provider;
	
	public String password_hash;
	
	public String password_salt;
	
	@OneToOne(cascade = CascadeType.ALL)
	public Wallet wallet;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Guess> guesses;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Score> scores;
	
	@OneToMany
	public List<Purchase> purchases;
	
	public User(String email, String username, String provider, String password) {
		this.email = email;
		this.username = username;
		this.provider = provider;
		this.password_hash = hashPassword(this, password);
		
		_User();
	}
	
	public User(Identity i) {
		this.username = i.identityId().userId();
		this.provider = i.identityId().providerId();
		this.first_name = i.firstName();
		this.last_name = i.lastName();
		this.full_name = i.fullName();
		if(i.email() instanceof Some) {
			this.email = i.email().get();
		}
		if(i.passwordInfo() instanceof Some) {
			this.password_hash = i.passwordInfo().get().password();
			if(i.passwordInfo().get().salt() instanceof Some)
				this.password_salt = i.passwordInfo().get().salt().get();
		}
		
		_User();
	}
	
	private void _User() {
		this.wallet = new Wallet();
		this.guesses = new ArrayList<Guess>();
		this.scores = new ArrayList<Score>();
		this.purchases = new ArrayList<Purchase>();
	}
	
	public static User findByIdentity(Identity i) {
		System.out.println(i.identityId());
		User user = User.find.where()
				  .eq("provider", i.identityId().providerId())
				  .eq("username", i.identityId().userId())
				.findUnique();
		
		return user;
	}
	
	public static String hashPassword(User user, String password) {
		if(user.password_salt == null) {
			user.password_salt = BCrypt.gensalt();
		}
		return BCrypt.hashpw(password, user.password_salt);
	}
	
	public static final Finder<Long, User> find 
		= new Finder<Long, User>(Long.class, User.class);

}