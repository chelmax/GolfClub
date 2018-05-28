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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Field.findAll", query = "SELECT f FROM Field f")
    , @NamedQuery(name = "Field.findByIdField", query = "SELECT f FROM Field f WHERE f.idField = :idField")
    , @NamedQuery(name = "Field.findBySize", query = "SELECT f FROM Field f WHERE f.size = :size")
    , @NamedQuery(name = "Field.findByFieldName", query = "SELECT f FROM Field f WHERE f.fieldName = :fieldName")
    , @NamedQuery(name = "Field.findByPricePerDay", query = "SELECT f FROM Field f WHERE f.pricePerDay = :pricePerDay")})
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_field")
    private Integer idField;
    @Basic(optional = false)
    @NotNull
    @Column(name = "size")
    private float size;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "field_name")
    private String fieldName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_per_day")
    private int pricePerDay;
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne(optional = false)
    private Category idCategory;
    @JoinColumn(name = "id_owner", referencedColumnName = "id_owner")
    @ManyToOne(optional = false)
    private Owner idOwner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idField")
    private Collection<Orders> ordersCollection;

    public Field() {
    }

    public Field(Integer idField) {
        this.idField = idField;
    }

    public Field(Integer idField, float size, String fieldName, int pricePerDay, int pricePerHour) {
        this.idField = idField;
        this.size = size;
        this.fieldName = fieldName;
        this.pricePerDay = pricePerDay;
    }

    public Integer getIdField() {
        return idField;
    }

    public void setIdField(Integer idField) {
        this.idField = idField;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public Owner getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Owner idOwner) {
        this.idOwner = idOwner;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idField != null ? idField.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        if ((this.idField == null && other.idField != null) || (this.idField != null && !this.idField.equals(other.idField))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.golfclub.entity.Field[ idField=" + idField + " ]";
    }
    
}
