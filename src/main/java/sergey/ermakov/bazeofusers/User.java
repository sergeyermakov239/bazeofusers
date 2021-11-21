package sergey.ermakov.bazeofusers;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column (name="password")
    private String password;
    @Column (name="age")
    private int age;

    public User(){

    }

    public  User(String name,int age,String password){
        this.name=name;
        this.age=age;
        this.password=password;
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

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "User [id="+id+", name="+name+", age=" +age+" ] ";
    }
}

