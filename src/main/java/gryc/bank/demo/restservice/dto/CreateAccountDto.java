package gryc.bank.demo.restservice.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CreateAccountDto {
    @NotEmpty
    @Length(max = 50)
    private final String name;
    @NotEmpty
    @Length(max = 50)
    private final String surname;

    public CreateAccountDto(@NotEmpty String name, @NotEmpty String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
