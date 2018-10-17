/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "rezerwacja")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Rezerwacja.findAll", query = "SELECT r FROM Rezerwacja r"),
    @NamedQuery(name = "Rezerwacja.findByIdRezerwacja", query = "SELECT r FROM Rezerwacja r WHERE r.idRezerwacja = :idRezerwacja"),
    @NamedQuery(name = "Rezerwacja.findByDataRez", query = "SELECT r FROM Rezerwacja r WHERE r.dataRez = :dataRez")
})
public class Rezerwacja implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rezerwacja")
    private Integer idRezerwacja;
    @Column(name = "data_rez")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRez;
    @JoinColumn(name = "id_klient", referencedColumnName = "id_klient")
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient idKlient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rezerwacja", fetch = FetchType.EAGER)
    private Set<RezerSeanse> rezerSeanseSet;

    public Rezerwacja()
    {
    }

    public Rezerwacja(Integer idRezerwacja)
    {
        this.idRezerwacja = idRezerwacja;
    }

    public Integer getIdRezerwacja()
    {
        return idRezerwacja;
    }

    public void setIdRezerwacja(Integer idRezerwacja)
    {
        this.idRezerwacja = idRezerwacja;
    }

    public Date getDataRez()
    {
        return dataRez;
    }

    public void setDataRez(Date dataRez)
    {
        this.dataRez = dataRez;
    }

    public Klient getIdKlient()
    {
        return idKlient;
    }

    public void setIdKlient(Klient idKlient)
    {
        this.idKlient = idKlient;
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
        hash += (idRezerwacja != null ? idRezerwacja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezerwacja))
        {
            return false;
        }
        Rezerwacja other = (Rezerwacja) object;
        if ((this.idRezerwacja == null && other.idRezerwacja != null) || (this.idRezerwacja != null && !this.idRezerwacja.equals(other.idRezerwacja)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Rezerwacja[ idRezerwacja=" + idRezerwacja + " ]";
    }
    
}
