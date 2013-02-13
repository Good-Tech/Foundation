package org.foundation.framework.mcl.persistence;



import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C) 2013 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 1/31/13 3:49 PM
 */
public interface GenericDAO<E extends IModel<ID>, ID extends Serializable> {
	public E getById(ID id);

	public E getByExample(E example);

	public List<E> getAll();

	public List<E> getAll(boolean cacheable, boolean sort);

	public List<E> getAllByExample(E example);

	public List<E> getAllByExample(E example, boolean cacheable, boolean sort);

	public int getCount();

	public int getCount(E entity);

	public boolean isExists(E entity);

	public void save(E entity);

	public void saveAll(List<E> entities);

	public void delete(E entity);
}
