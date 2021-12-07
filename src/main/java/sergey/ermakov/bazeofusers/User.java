package sergey.ermakov.bazeofusers;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty("firstname")
    @Column(name="firstname")
    private String firstname;
    @JsonProperty ("lastname")
    @Column(name="lastname")
    private String lastname;
    @JsonProperty ("password")
    @Column(name="password")
    private String password;
    @JsonProperty("age")
    @Column(name="age")
    private int age;

    public User(){

    }

    public  User(String firstname,String lastname,int age,String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.age=age;
        this.password=password;
    }
    public User(String firstname,String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
        this.age=18;
        this.password="password";
    }

    public long getId(){
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "User [id="+id+", name="+firstname+", lastname="+lastname+", age=" +age+" ] ";
    }
}

