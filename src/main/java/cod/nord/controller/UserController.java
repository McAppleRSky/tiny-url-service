package cod.nord.controller;

import cod.nord.service.UserService;
import cod.nord.service.model.UserRequest;
import cod.nord.service.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//@Tag(name = "Person REST API operations")
@RestController
@RequestMapping("/api/0.0.1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //@Operation( summary = "Get all persons", responses = @ApiResponse(responseCode = "200", description = "Persons", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonShortResponse.class)))) )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> users() {
        return userService.findAll();
    }

    /*@Operation( summary = "Get person by ID", responses = { @ApiResponse(responseCode = "200", description = "Person for requested ID", content = @Content(schema = @Schema(implementation = PersonFullResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) } )*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse user(@PathVariable Integer id) {
        return userService.getById(id);
    }

    /*@Operation( summary = "Create new person", responses = { @ApiResponse(responseCode = "201", description = "New person is created"),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))) } )*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody UserRequest requested) {
        final int id = userService.create(requested);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    /*@Operation( summary = "Update existing person", responses = { @ApiResponse(responseCode = "200", description = "Person for requested ID is updated", content = @Content(schema = @Schema(implementation = PersonFullResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) } )*/
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse update(@PathVariable Integer id, @Valid @RequestBody UserRequest requested) {
        return userService.update(id, requested);
    }

    //@Operation( summary = "Remove person for ID", responses = @ApiResponse(responseCode = "204", description = "Person for requested ID is removed") )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        userService.delete(id);
    }

}
