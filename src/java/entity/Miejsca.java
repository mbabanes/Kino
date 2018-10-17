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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "miejsca")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Miejsca.findAll", query = "SELECT m FROM Miejsca m"),
    @NamedQuery(name = "Miejsca.findByIdMiejsca", query = "SELECT m FROM Miejsca m WHERE m.idMiejsca = :idMiejsca"),
    @NamedQuery(name = "Miejsca.findByNrrzedu", query = "SELECT m FROM Miejsca m WHERE m.nrrzedu = :nrrzedu"),
    @NamedQuery(name = "Miejsca.findByNrMiejsca", query = "SELECT m FROM Miejsca m WHERE m.nrMiejsca = :nrMiejsca"),
    @NamedQuery(name = "Miejsca.findByWolne", query = "SELECT m FROM Miejsca m WHERE m.wolne = :wolne"),
    @NamedQuery(name = "Miejsca.findByIdSeans", query = "SELECT m FROM Miejsca m WHERE m.idSeans = :idSeans"),
    @NamedQuery(name = "Miejsca.findUnavilableByIdSeans", query = "SELECT m FROM Miejsca m WHERE m.idSeans = :idSeans AND m.wolne = false ORDER BY m.nrrzedu, m.nrMiejsca"),
    @NamedQuery(name = "Miejsca.findByAllFree", query = "SELECT m FROM Miejsca m WHERE m.idSeans = :idSeans AND m.wolne = true")
})
public class Miejsca implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_miejsca")
    private Integer idMiejsca;
    @Size(max = 2)
    @Column(name = "Nr_rzedu")
    private String nrrzedu;
    @Column(name = "Nr_Miejsca")
    private Integer nrMiejsca;
    @Column(name = "wolne")
    private Boolean wolne;
    @OneToMany(mappedBy = "idMiejsca", fetch = FetchType.EAGER)
    private Set<Bilet> biletSet;
    @JoinColumn(name = "id_seans", referencedColumnName = "id_seans")
    @ManyToOne(fetch = FetchType.EAGER)
    private Seans idSeans;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miejsca", fetch = FetchType.EAGER)
    private Set<RezerSeanse> rezerSeanseSet;
    
    

    public Miejsca()
    {
    }

    public Miejsca(Integer idMiejsca)
    {
        this.idMiejsca = idMiejsca;
    }

    public Integer getIdMiejsca()
    {
        return idMiejsca;
    }

    public void setIdMiejsca(Integer idMiejsca)
    {
        this.idMiejsca = idMiejsca;
    }

    public String getNrrzedu()
    {
        return nrrzedu;
    }

    public void setNrrzedu(String nrrzedu)
    {
        this.nrrzedu = nrrzedu;
    }

    public Integer getNrMiejsca()
    {
        return nrMiejsca;
    }

    public void setNrMiejsca(Integer nrMiejsca)
    {
        this.nrMiejsca = nrMiejsca;
    }

    public Boolean getWolne()
    {
        return wolne;
    }

    public void setWolne(Boolean wolne)
    {
        this.wolne = wolne;
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

    public Seans getIdSeans()
    {
        return idSeans;
    }

    public void setIdSeans(Seans idSeans)
    {
        this.idSeans = idSeans;
    }

    @XmlTransient
    public Set<RezerSeanse> getRezerSeanseSet()
    {
        return rezerSeanseSet;
    }

    public void setRezerSeanseSet(Set<RezerSeanse> rezerSeanseSet)
    {
        this.rezerSeanseSet = rezerSeanseSet;
    }

 

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMiejsca != null ? idMiejsca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miejsca))
        {
            return false;
        }
        Miejsca other = (Miejsca) object;
        if ((this.idMiejsca == null && other.idMiejsca != null) || (this.idMiejsca != null && !this.idMiejsca.equals(other.idMiejsca)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Miejsca[ idMiejsca=" + idMiejsca + " ]";
    }
    
}
