package sergey.ermakov.bazeofusers;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class Controller {
    private final UserRepository userRepository;

    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//curl -X POST http://localhost:8080/api/users/postuser/password   -H 'Content-Type: application/json' -d'{"name":"Sergey","age":17,"password":"password"}'
    @PostMapping("/users/postuser/{repeatpassword}")
    public void addUser(@PathVariable("repeatpassword") String repeatpassword,@RequestBody User user){
        if (!repeatpassword.equals(user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            ArrayList<User> a = new ArrayList<>();
            userRepository.findAll().forEach(a::add);
            for (User u : a) {
                if (user.getName().equals(u.getName())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                }
            }
            userRepository.save(user);
        }
    }

    @GetMapping("/users/getall")
    public ArrayList<String> getUsers (){
        ArrayList<User> a =new ArrayList<>();
        userRepository.findAll().forEach(a::add);
        ArrayList<String> b= new ArrayList<>();
        for (User u:a){
            b.add(u.toString());
        }
        return b;
    }

    @GetMapping("/users/getbyid/{id}")
    public String getUserByid(@PathVariable("id") long id){
        Optional<User> a =userRepository.findById(id);
        if (!a.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else{
            User u1=a.get();
            return u1.toString();
        }
    }
//curl -X DELETE http://localhost:8080/api/users/deleteuser/1234567890
    @DeleteMapping("/users/deleteuser/{id}")
    public void deleteUserbyid (@PathVariable("id") long id){
        Optional<User> a=userRepository.findById(id);
        if (!a.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else{
            userRepository.deleteById(id);
        }
    }
//curl -X PUT http://localhost:8080/api/users/updateuser/1234567890/password -H 'Content-Type: application/json' -d '{"name":"Sergey","age":17,"password":"password1"}'
    @PutMapping("/users/updateuser/{id}/{repeatpassword}")
    public void updateUserbyid (@PathVariable("repeatpassword") String repeatpassword,@PathVariable("id") long id,@RequestBody User user) {
        if (!repeatpassword.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            Optional<User> a = userRepository.findById(id);
            if (!a.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                User u1 = a.get();
                u1.setAge(user.getAge());
                u1.setName(user.getName());
                u1.setPassword(user.getPassword());
                userRepository.save(u1);
            }
        }
    }

    @GetMapping("/users/getbyage/{age}")
    public ArrayList<String> getusersbyage(@PathVariable("age") int age){
        ArrayList<String> a =new ArrayList<>();
        for (int i=age-5;i<=age+5;i++){
            List<User> arr=userRepository.findByAge(age);
            for (User u:arr){
                a.add(u.toString());
            }
        };
        return a;
    }

    @GetMapping("/users/getsorted")
    public ArrayList<String> getuserssorted(@RequestParam("sort") String sort){
        ArrayList<String> str=new ArrayList<>();
        if (sort.equals("age")){
            List<User> a=userRepository.findByOrderByAgeAsc();
            for (User u:a){
                str.add(u.toString());
            }
            return str;
        } else if (sort.equals("name")){
            List<User> a=userRepository.findByOrderByNameAsc();
            for (User u:a){
                str.add(u.toString());
            }
            return str;
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/getsortedwithdirection")
    public ArrayList<String> getuserssortedwithdirection (@RequestParam("sort") String sort,@RequestParam("sortdirection") String sortdirection){
        ArrayList<String> str=new ArrayList<>();
        if (sort.equals("age")){
            List<User> a;
            if (sortdirection.equals("up")) {
                a = userRepository.findAll(Sort.by(Sort.Direction.ASC,"age"));
            } else if (sortdirection.equals("down")) {
                a = userRepository.findAll(Sort.by(Sort.Direction.DESC,"age"));
            } else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            };
            for (User u:a){
                str.add(u.toString());
            }
            return str;
        } else if (sort.equals("name")){
            List<User> a;
            if (sortdirection.equals("up")) {
                a = userRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
            } else if (sortdirection.equals("down")) {
                a = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
            } else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            };
            for (User u:a){
                str.add(u.toString());
            }
            return str;
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }




}