package orlanda.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import orlanda.dto.PersonDTO;
import orlanda.models.Person;
import orlanda.services.PeopleService;
import orlanda.util.InvalidPersonException;
import orlanda.util.PersonErrorResponse;
import orlanda.util.PersonNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PersonDTO> index() {
        return peopleService.getAll().stream().map(this::convertPersonToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO show(@PathVariable int id) {
        return convertPersonToDTO(peopleService.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(fieldError -> {
                errorMessage.append(fieldError.getDefaultMessage());
                errorMessage.append("; ");
            });

            throw new InvalidPersonException(errorMessage.toString());
        }

        Person person = convertDTOToPerson(personDTO);
        peopleService.save(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handle(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse("Person not found", System.currentTimeMillis());

        // HTTP-ответ с телом response и со статусом 404 в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handle(InvalidPersonException e) {
        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertDTOToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertPersonToDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

}
