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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "filmy")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Filmy.findAll", query = "SELECT f FROM Filmy f"),
    @NamedQuery(name = "Filmy.findByIdFilm", query = "SELECT f FROM Filmy f WHERE f.idFilm = :idFilm"),
    @NamedQuery(name = "Filmy.findByTytul", query = "SELECT f FROM Filmy f WHERE f.tytul = :tytul"),
    @NamedQuery(name = "Filmy.findByCzasTrwania", query = "SELECT f FROM Filmy f WHERE f.czasTrwania = :czasTrwania"),
    @NamedQuery(name = "Filmy.findByDataPremiery", query = "SELECT f FROM Filmy f WHERE f.dataPremiery = :dataPremiery")
})
public class Filmy implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_film")
    private Integer idFilm;
    @Size(max = 30)
    @Column(name = "Tytul")
    private String tytul;
    @Basic(optional = false)
    @NotNull
    @Column(name = "czas_trwania")
    private int czasTrwania;
    @Column(name = "data_premiery")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPremiery;
    @JoinTable(name = "filmy_seans", joinColumns =
    {
        @JoinColumn(name = "id_film", referencedColumnName = "id_film")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "id_seans", referencedColumnName = "id_seans")
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Seans> seansSet;

    public Filmy()
    {
    }

    public Filmy(Integer idFilm)
    {
        this.idFilm = idFilm;
    }

    public Filmy(Integer idFilm, int czasTrwania)
    {
        this.idFilm = idFilm;
        this.czasTrwania = czasTrwania;
    }

    public Integer getIdFilm()
    {
        return idFilm;
    }

    public void setIdFilm(Integer idFilm)
    {
        this.idFilm = idFilm;
    }

    public String getTytul()
    {
        return tytul;
    }

    public void setTytul(String tytul)
    {
        this.tytul = tytul;
    }

    public int getCzasTrwania()
    {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania)
    {
        this.czasTrwania = czasTrwania;
    }

    public Date getDataPremiery()
    {
        return dataPremiery;
    }

    public void setDataPremiery(Date dataPremiery)
    {
        this.dataPremiery = dataPremiery;
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
        hash += (idFilm != null ? idFilm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filmy))
        {
            return false;
        }
        Filmy other = (Filmy) object;
        if ((this.idFilm == null && other.idFilm != null) || (this.idFilm != null && !this.idFilm.equals(other.idFilm)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Filmy[ idFilm=" + idFilm + " ]";
    }
    
}
