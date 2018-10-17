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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "seans")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Seans.findAll", query = "SELECT s FROM Seans s"),
    @NamedQuery(name = "Seans.findByIdSeans", query = "SELECT s FROM Seans s WHERE s.idSeans = :idSeans"),
    @NamedQuery(name = "Seans.findByData", query = "SELECT s FROM Seans s WHERE s.data = :data"),
    @NamedQuery(name = "Seans.findByGodzina", query = "SELECT s FROM Seans s WHERE s.godzina = :godzina"),
    @NamedQuery(name = "Seans.findByCzy3d", query = "SELECT s FROM Seans s WHERE s.czy3d = :czy3d"),
    @NamedQuery(name = "Seans.findByCzyDubbing", query = "SELECT s FROM Seans s WHERE s.czyDubbing = :czyDubbing"),
    @NamedQuery(name = "Seans.findByCzyNapisy", query = "SELECT s FROM Seans s WHERE s.czyNapisy = :czyNapisy")
})
public class Seans implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seans")
    private Integer idSeans;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godzina")
    @Temporal(TemporalType.TIME)
    private Date godzina;
    @Column(name = "czy3d")
    private Boolean czy3d;
    @Column(name = "czyDubbing")
    private Boolean czyDubbing;
    @Column(name = "czyNapisy")
    private Boolean czyNapisy;
    @ManyToMany(mappedBy = "seansSet", fetch = FetchType.EAGER)
    private Set<Filmy> filmySet;
    @OneToMany(mappedBy = "idSeans", fetch = FetchType.EAGER)
    private Set<Bilet> biletSet;
    @OneToMany(mappedBy = "idSeans", fetch = FetchType.EAGER)
    private Set<Miejsca> miejscaSet;
    @JoinColumn(name = "Id_Lokalicacja", referencedColumnName = "Id_Lokalicacja")
    @ManyToOne(fetch = FetchType.EAGER)
    private Lokalizacja idLokalicacja;
    @JoinColumn(name = "id_sali", referencedColumnName = "id_sali")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sale idSali;
    @OneToMany(mappedBy = "idSeans", fetch = FetchType.EAGER)
    private Set<RezerSeanse> rezerSeanseSet;


    public Seans()
    {
    }

    public Seans(Integer idSeans)
    {
        this.idSeans = idSeans;
    }

    public Seans(Integer idSeans, Date godzina)
    {
        this.idSeans = idSeans;
        this.godzina = godzina;
    }

    public Integer getIdSeans()
    {
        return idSeans;
    }

    public void setIdSeans(Integer idSeans)
    {
        this.idSeans = idSeans;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Date getGodzina()
    {
        return godzina;
    }

    public void setGodzina(Date godzina)
    {
        this.godzina = godzina;
    }

    public Boolean getCzy3d()
    {
        return czy3d;
    }

    public void setCzy3d(Boolean czy3d)
    {
        this.czy3d = czy3d;
    }

    public Boolean getCzyDubbing()
    {
        return czyDubbing;
    }

    public void setCzyDubbing(Boolean czyDubbing)
    {
        this.czyDubbing = czyDubbing;
    }

    public Boolean getCzyNapisy()
    {
        return czyNapisy;
    }

    public void setCzyNapisy(Boolean czyNapisy)
    {
        this.czyNapisy = czyNapisy;
    }

    @XmlTransient
    public Set<Filmy> getFilmySet()
    {
        return filmySet;
    }

    public void setFilmySet(Set<Filmy> filmySet)
    {
        this.filmySet = filmySet;
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

    @XmlTransient
    public Set<Miejsca> getMiejscaSet()
    {
        return miejscaSet;
    }

    public void setMiejscaSet(Set<Miejsca> miejscaSet)
    {
        this.miejscaSet = miejscaSet;
    }

    public Lokalizacja getIdLokalicacja()
    {
        return idLokalicacja;
    }

    public void setIdLokalicacja(Lokalizacja idLokalicacja)
    {
        this.idLokalicacja = idLokalicacja;
    }

    public Sale getIdSali()
    {
        return idSali;
    }

    public void setIdSali(Sale idSali)
    {
        this.idSali = idSali;
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
        hash += (idSeans != null ? idSeans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seans))
        {
            return false;
        }
        Seans other = (Seans) object;
        if ((this.idSeans == null && other.idSeans != null) || (this.idSeans != null && !this.idSeans.equals(other.idSeans)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Seans[ idSeans=" + idSeans + " ]";
    }
    
}
