package br.com.infrastructure.DTO.OUT;

import br.com.domain.entities.Role;
import java.util.List;
import java.util.stream.Collectors;

public record RoleOutList(Integer id, String name) {

	public static List<RoleOutList> roleOutList (List<Role> entityList){
		return entityList.stream().map(x-> new RoleOutList(x.getId(), x.getName())).collect(Collectors.toList());
	}
}
