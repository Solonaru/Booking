package com.project.entities;

import java.util.List;
import java.util.Optional;

public interface IEntityController<Entity, ID> {

	public Optional<Entity> findById(ID entityId);

	public List<Entity> getAll();

	public void insert(Entity entity);

	public void update(Entity entity);

	public void delete(ID entityId);

}
