/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;

/**
 *
 * @author Richard
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "BusquedaEntity.findAll", query = "SELECT i FROM BusquedaEntity i"),
    @NamedQuery(name = "BusquedaEntity.findById", query = "SELECT i FROM BusquedaEntity i WHERE i.id = :id"),
    @NamedQuery(name = "BusquedaEntity.findByUsuario", query = "SELECT i FROM BusquedaEntity i WHERE i.usuario = :usuario")
   })

public class BusquedaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn
    @ManyToOne
    private UsuarioEntity usuario;
    @JoinColumn ( name ="ingredientes")
    @OneToMany
    private List<IngredienteEntity> ingredientes;
    @JoinColumn ( name ="recetas")
    @OneToMany
    private List<RecetaEntity> recetas;
    @Column ( name ="fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<IngredienteEntity> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteEntity> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<RecetaEntity> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaEntity> recetas) {
        this.recetas = recetas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusquedaEntity)) {
            return false;
        }
        BusquedaEntity other = (BusquedaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.edu.ort.entidades.BusquedaEntity[ id=" + id + " ]";
    }
    
}
