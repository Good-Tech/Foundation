package org.foundation.warehouse;



import org.foundation.Foundation;
import org.goodgod.controller.MessageHandler;

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
 * Created 2/5/13 12:17 AM
 */
public interface Warehouse
{
    /**
     * Save a foundational entity in active persistence.
     *
     * @param foundation
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Save a foundational entity in active persistence.",
            group = "warehouse",
            channel = "save-foundation"
    )
    public <FoundationalEntity extends Foundation> void save(FoundationalEntity foundation);

    /**
     * Archive a foundational entity. This will deactivate it from active persistence.
     *
     *
     * @param foundation
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Archive a foundational entity. This will deactivate it from active persistence.",
            group = "warehouse",
            channel = "archive-foundation"
    )
    public <FoundationalEntity extends Foundation> void archive(FoundationalEntity foundation);

    /**
     * Load a foundational entity by it's unique ID.
     *
     * @param foundationId
     * @param <FoundationalEntity>
     *
     * @return
     */
    @MessageHandler(
            documentation = "Load a foundational entity by it's unique ID.",
            group = "warehouse",
            channel = "load-foundation"
    )
    public <FoundationalEntity extends Foundation> FoundationalEntity load(String foundationId);

    /**
     * Disable a foundational entity.
     *
     * This will archive the entity in a deactivated state.
     *
     * @param foundationalEntity
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Disable a foundational entity.",
            group = "warehouse",
            channel = "disable-foundation"
    )
    public <FoundationalEntity extends Foundation> void disable(FoundationalEntity foundationalEntity);

    /**
     * Enable a foundational entity for access.
     *
     * This will change the persistence state of the specified entity to {@link PersistenceState#Archived}
     *
     * @param foundationId
     * @param type
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Enable a foundational entity for access.",
            group = "warehouse",
            channel = "enable-foundation"
    )
    public <FoundationalEntity extends Foundation> void enable(String foundationId, Class<? extends FoundationalEntity> type);

    /**
     * Restrict access to a foundational entity.
     *
     * This will archive the entity in a restricted state.
     *
     * @param foundationalEntity
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Restrict access to a foundational entity.",
            group = "warehouse",
            channel = "restrict-foundation"
    )
    public <FoundationalEntity extends Foundation> void restrict(FoundationalEntity foundationalEntity);

    /**
     * Unrestrict access to a foundational entity.
     *
     * This will change the persistence state of the specified entity to {@link PersistenceState#Disabled}
     *
     * @param foundationId
     * @param type
     * @param <FoundationalEntity>
     */
    @MessageHandler(
            documentation = "Unrestrict access to a foundational entity.",
            group = "warehouse",
            channel = "unrestrict-foundation"
    )
    public <FoundationalEntity extends Foundation> void unrestrict(String foundationId, Class<? extends FoundationalEntity> type);

}
