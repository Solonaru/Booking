package com.project.entities;

import java.util.List;
import java.util.Optional;

public interface IEntityService<Entity, ID> {
	
	public Optional<Entity> findById(ID entityId);

	public List<Entity> findAll();

	public void insert(Entity entity);

	public void update(Entity entity);

	public void deleteById(ID entityId);
	
}
