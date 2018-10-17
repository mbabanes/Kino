/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Mbabane
 */
@Entity
@Table(name = "lokalizacja")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Lokalizacja.findAll", query = "SELECT l FROM Lokalizacja l"),
    @NamedQuery(name = "Lokalizacja.findByIdLokalicacja", query = "SELECT l FROM Lokalizacja l WHERE l.idLokalicacja = :idLokalicacja"),
    @NamedQuery(name = "Lokalizacja.findByAdres", query = "SELECT l FROM Lokalizacja l WHERE l.adres = :adres"),
    @NamedQuery(name = "Lokalizacja.findByMiasto", query = "SELECT l FROM Lokalizacja l WHERE l.miasto = :miasto")
})
public class Lokalizacja implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Lokalicacja")
    private Integer idLokalicacja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "adres")
    private String adres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "miasto")
    private String miasto;
    @OneToMany(mappedBy = "idLokalicacja", fetch = FetchType.EAGER)
    private Set<Pracownik> pracownikSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLokalicacja", fetch = FetchType.EAGER)
    private Set<Sale> saleSet;
    @OneToMany(mappedBy = "idLokalicacja", fetch = FetchType.EAGER)
    private Set<Seans> seansSet;

    public Lokalizacja()
    {
    }

    public Lokalizacja(Integer idLokalicacja)
    {
        this.idLokalicacja = idLokalicacja;
    }

    public Lokalizacja(Integer idLokalicacja, String adres, String miasto)
    {
        this.idLokalicacja = idLokalicacja;
        this.adres = adres;
        this.miasto = miasto;
    }

    public Integer getIdLokalicacja()
    {
        return idLokalicacja;
    }

    public void setIdLokalicacja(Integer idLokalicacja)
    {
        this.idLokalicacja = idLokalicacja;
    }

    public String getAdres()
    {
        return adres;
    }

    public void setAdres(String adres)
    {
        this.adres = adres;
    }

    public String getMiasto()
    {
        return miasto;
    }

    public void setMiasto(String miasto)
    {
        this.miasto = miasto;
    }

    @XmlTransient
    public Set<Pracownik> getPracownikSet()
    {
        return pracownikSet;
    }

    public void setPracownikSet(Set<Pracownik> pracownikSet)
    {
        this.pracownikSet = pracownikSet;
    }

    @XmlTransient
    public Set<Sale> getSaleSet()
    {
        return saleSet;
    }

    public void setSaleSet(Set<Sale> saleSet)
    {
        this.saleSet = saleSet;
    }

    @XmlTransient
    public Set<Seans> getSeansSet()
    {
        return seansSet;
    }

    public void setSeansSet(Set<Seans> seansSet)
    {
        this.seansSet = seansSet;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idLokalicacja != null ? idLokalicacja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokalizacja))
        {
            return false;
        }
        Lokalizacja other = (Lokalizacja) object;
        if ((this.idLokalicacja == null && other.idLokalicacja != null) || (this.idLokalicacja != null && !this.idLokalicacja.equals(other.idLokalicacja)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Lokalizacja[ idLokalicacja=" + idLokalicacja + " ]";
    }
    
}
