package orlanda.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message="Name shouldn't be empty")
    @Size(min=2, max=30, message="Incorrect name size")
    private String name;

    @Min(value=0, message="Invalid age")
    private int age;

    @NotEmpty(message="E-mail shouldn't be empty")
    @Email(message="Invalid e-mail")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
