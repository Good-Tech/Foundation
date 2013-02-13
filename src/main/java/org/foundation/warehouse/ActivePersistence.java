package org.foundation.warehouse;



import org.foundation.Foundation;

/**
 * The active responder to persistence for the data warehouse.
 * Simple CRUD
 *
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
 * Created 2/5/13 12:37 AM
 */
public interface ActivePersistence
{
    /**
     * Create a foundational entity in active persistence.
     *
     * @param foundation
     * @param <FoundationalEntity>
     */
    public <FoundationalEntity extends Foundation> void create(FoundationalEntity foundation);

    /**
     * Read a foundational entity from active persistence.
     *
     * @param foundationId
     * @param type
     * @param <FoundationalEntity>
     * @return
     */
    public <FoundationalEntity extends Foundation> Foundation read(String foundationId, Class<? extends FoundationalEntity> type);

    /**
     * Update a foundational entity in active persistence.
     *
     * @param foundation
     * @param <FoundationalEntity>
     */
    public <FoundationalEntity extends Foundation> FoundationalEntity update(FoundationalEntity foundation);

    /**
     * Delete a foundational entity from active persistence.
     *
     * @param foundation
     * @param <FoundationalEntity>
     */
    public <FoundationalEntity extends Foundation> void delete(FoundationalEntity foundation);

    /**
     *
     * @param type
     * @param key
     * @param value
     * @param <FoundationalEntity>
     * @return
     */
    public <FoundationalEntity extends Foundation> FoundationalEntity findByProperty(Class<? extends Foundation> type, String key, String value);

    public <FoundationalEntity extends Foundation> FoundationalEntity findByProperty(Class<? extends Foundation> type, String key, Foundation value);
}
