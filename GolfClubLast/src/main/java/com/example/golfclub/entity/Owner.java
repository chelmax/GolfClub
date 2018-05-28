/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.golfclub.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author max19
 */
@Entity
@Table(name = "owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o")
    , @NamedQuery(name = "Owner.findByIdOwner", query = "SELECT o FROM Owner o WHERE o.idOwner = :idOwner")
    , @NamedQuery(name = "Owner.findByName", query = "SELECT o FROM Owner o WHERE o.name = :name")
    , @NamedQuery(name = "Owner.findByContactAddress", query = "SELECT o FROM Owner o WHERE o.contactAddress = :contactAddress")
    , @NamedQuery(name = "Owner.findByContactPhone", query = "SELECT o FROM Owner o WHERE o.contactPhone = :contactPhone")})
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_owner")
    private Integer idOwner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contact_address")
    private String contactAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contact_phone")
    private String contactPhone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOwner")
    private Collection<Field> fieldCollection;

    public Owner() {
    }

    public Owner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Owner(Integer idOwner, String name, String contactAddress, String contactPhone) {
        this.idOwner = idOwner;
        this.name = name;
        this.contactAddress = contactAddress;
        this.contactPhone = contactPhone;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @XmlTransient
    public Collection<Field> getFieldCollection() {
        return fieldCollection;
    }

    public void setFieldCollection(Collection<Field> fieldCollection) {
        this.fieldCollection = fieldCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOwner != null ? idOwner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.idOwner == null && other.idOwner != null) || (this.idOwner != null && !this.idOwner.equals(other.idOwner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.golfclub.entity.Owner[ idOwner=" + idOwner + " ]";
    }
    
}
