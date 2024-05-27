package br.com.infrastructure.http;

import br.com.application.services.UserService;
import br.com.domain.interfaces.services.IuserService;
import br.com.infrastructure.DTO.IN.UserIn;
import br.com.infrastructure.DTO.IN.UserInPatch;
import br.com.infrastructure.DTO.IN.UserInUpdate;
import br.com.infrastructure.DTO.IN.UserInLogin;
import br.com.infrastructure.DTO.OUT.LoginOut;
import br.com.infrastructure.DTO.OUT.UserOut;
import br.com.infrastructure.DTO.OUT.UserOutList;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1")
public class UserController {

	private final IuserService iuserService;

	public UserController(UserService userService, IuserService iuserService) {
		this.iuserService = iuserService;
	}

	@PostMapping
	public ResponseEntity<Long> save(@RequestBody UserIn request) {
		var id = iuserService.save(request.toDomainWithRole());
		return ResponseEntity.created(URI.create("/" + id)).body(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserOut>> findById(@PathVariable Long id) {
		var result = iuserService.findById(id);
		var response = UserOut.toOutOptional(result);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping()
	public ResponseEntity<List<UserOutList>> findAll() {
		var listDomain = iuserService.findAll();
		var response = UserOutList.listToOut(listDomain);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		iuserService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody UserInUpdate requestBody) {
		iuserService.update(id, requestBody);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity patchPassword(@RequestBody UserInPatch requestBody) {
		iuserService.patchPassword(requestBody);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginOut> login(@RequestBody UserInLogin user) {
		var result = iuserService.login(user);
		return ResponseEntity.ok().body(result);
	}
}


