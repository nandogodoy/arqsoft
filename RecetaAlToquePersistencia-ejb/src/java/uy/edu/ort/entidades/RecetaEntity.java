/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Richard
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "RecetaEntity.findAll", query = "SELECT i FROM RecetaEntity i"),
    @NamedQuery(name = "RecetaEntity.findById", query = "SELECT i FROM RecetaEntity i WHERE i.id = :id"),
    @NamedQuery(name = "RecetaEntity.findByNombre", query = "SELECT i FROM RecetaEntity i WHERE i.nombre = :nombre")
   })

public class RecetaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UsuarioEntity usuario;
    
    @Column (name="nombre")
    private String nombre;
    
    @Column(name="valoracion")
    private float valoracion;
    
    @Column (name="procedimiento")
    private String procedimiento;
    
    @OneToMany
    @ElementCollection
    private List<IngredienteEntity> ingredientes;
    
    @Column (name="cantvaloraciones")
    private int cantValoraciones;
    
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
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
        if (!(object instanceof RecetaEntity)) {
            return false;
        }
        RecetaEntity other = (RecetaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<IngredienteEntity> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteEntity> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getCantValoraciones() {
        return cantValoraciones;
    }

    public void setCantValoraciones(int cantValoraciones) {
        this.cantValoraciones = cantValoraciones;
    }
    
    @Override
    public String toString() {
        return "uy.edu.ort.dominio.Receta[ id=" + id + " ]";
    }
    
}
