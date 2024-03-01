package br.com.infrastructure.DTO.OUT;

import br.com.domain.entities.Role;
import br.com.domain.entities.User;
import java.util.List;
import java.util.stream.Collectors;

public record UserOutList(Long id, String name, List<Role> roleList) {

	public static List<UserOutList> listToOut (List<User> entityList){
		return entityList.stream().map(x-> new UserOutList(x.getId(), x.getName(), x.getRoleList())).collect(Collectors.toList());
	}
}
