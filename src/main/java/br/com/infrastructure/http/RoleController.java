package br.com.infrastructure.http;

import br.com.domain.interfaces.services.IroleService;
import br.com.infrastructure.DTO.IN.RoleIn;
import br.com.infrastructure.DTO.OUT.RoleOut;
import br.com.infrastructure.DTO.OUT.RoleOutList;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

	private final IroleService iroleService;

	public RoleController(IroleService iroleService) {
		this.iroleService = iroleService;
	}

	@PostMapping
	public ResponseEntity<RoleOut> insert(@RequestBody RoleIn request) {
		var result = iroleService.insert(request.toDomain());
		var out = RoleOut.toOut(result);
		return ResponseEntity.created(URI.create("/" + out.id())).body(out);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Optional<RoleOut>> findById(@PathVariable int id){
		var domain = iroleService.findById(id);
		var out = RoleOut.outOptional(domain);
		return ResponseEntity.ok().body(out);
	}
	@GetMapping
	public ResponseEntity<List<RoleOutList>> findAll(){
		var listDomain = iroleService.findAll();
		return ResponseEntity.ok().body(RoleOutList.roleOutList(listDomain));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteById(@PathVariable int id){
		iroleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable int id, @RequestBody RoleIn requestBody){
		iroleService.update(requestBody.toDomain(id));
		return ResponseEntity.noContent().build();
	}

}
