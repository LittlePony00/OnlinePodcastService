package com.littlepony.PodcastService.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

        private UUID uuid;

        protected BaseEntity() {}

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        public UUID getUuid() { return uuid; }

        public void setUuid(UUID uuid) { this.uuid = uuid; }
}
