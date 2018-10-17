/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "rodzaj_biletu")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RodzajBiletu.findAll", query = "SELECT r FROM RodzajBiletu r"),
    @NamedQuery(name = "RodzajBiletu.findByIdRodzBi", query = "SELECT r FROM RodzajBiletu r WHERE r.idRodzBi = :idRodzBi"),
    @NamedQuery(name = "RodzajBiletu.findByRodzaj", query = "SELECT r FROM RodzajBiletu r WHERE r.rodzaj = :rodzaj"),
    @NamedQuery(name = "RodzajBiletu.findByCena", query = "SELECT r FROM RodzajBiletu r WHERE r.cena = :cena")
})
public class RodzajBiletu implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rodz_bi")
    private Integer idRodzBi;
    @Size(max = 10)
    @Column(name = "Rodzaj")
    private String rodzaj;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private BigDecimal cena;
    @OneToMany(mappedBy = "idRodzBi", fetch = FetchType.EAGER)
    private Set<Bilet> biletSet;
    

    public RodzajBiletu()
    {
    }

    public RodzajBiletu(Integer idRodzBi)
    {
        this.idRodzBi = idRodzBi;
    }

    public Integer getIdRodzBi()
    {
        return idRodzBi;
    }

    public void setIdRodzBi(Integer idRodzBi)
    {
        this.idRodzBi = idRodzBi;
    }

    public String getRodzaj()
    {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj)
    {
        this.rodzaj = rodzaj;
    }

    public BigDecimal getCena()
    {
        return cena;
    }

    public void setCena(BigDecimal cena)
    {
        this.cena = cena;
    }

    @XmlTransient
    public Set<Bilet> getBiletSet()
    {
        return biletSet;
    }

    public void setBiletSet(Set<Bilet> biletSet)
    {
        this.biletSet = biletSet;
    }

  

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idRodzBi != null ? idRodzBi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RodzajBiletu))
        {
            return false;
        }
        RodzajBiletu other = (RodzajBiletu) object;
        if ((this.idRodzBi == null && other.idRodzBi != null) || (this.idRodzBi != null && !this.idRodzBi.equals(other.idRodzBi)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.RodzajBiletu[ idRodzBi=" + idRodzBi + " ]";
    }
    
}
