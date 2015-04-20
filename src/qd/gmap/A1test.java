package qd.gmap;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class A1test {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String s1;
	@Persistent
	private String s2;
	@Persistent
	private String s3;
	@Persistent
	private String s4;
	@Persistent
	private String s5;


	public A1test(String s1, String s2, String s3, String s4, String s5) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
	}

	public String get_s1(){return s1;}
	public String get_s2(){return s2;}
	public String get_s3(){return s3;}
	public String get_s4(){return s4;}
	public String get_s5(){return s5;}

	public void set_s1(String s){this.s1 = s;}
	public void set_s2(String s){this.s2 = s;}
	public void set_s3(String s){this.s3 = s;}
	public void set_s4(String s){this.s4 = s;}
	public void set_s5(String s){this.s5 = s;}
	
}