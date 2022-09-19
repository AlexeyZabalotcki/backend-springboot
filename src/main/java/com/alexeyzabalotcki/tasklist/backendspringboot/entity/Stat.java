package com.alexeyzabalotcki.tasklist.backendspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }

    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }

}
