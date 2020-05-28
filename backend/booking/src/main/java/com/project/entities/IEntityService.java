package com.project.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IEntityService<Entity, ID> {
	
	public Optional<Entity> findById(ID entityId);

	public List<Entity> findAll();

	public void insert(Entity entity);

	public void update(Entity entity);

	public void deleteById(ID entityId);
	
}
