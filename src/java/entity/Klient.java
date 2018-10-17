/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "klient")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k"),
    @NamedQuery(name = "Klient.findByIdKlient", query = "SELECT k FROM Klient k WHERE k.idKlient = :idKlient"),
    @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie"),
    @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko"),
    @NamedQuery(name = "Klient.findByHaslo", query = "SELECT k FROM Klient k WHERE k.haslo = :haslo"),
    @NamedQuery(name = "Klient.findByEmail", query = "SELECT k FROM Klient k WHERE k.email = :email"),
    @NamedQuery(name = "Klient.findByFirma", query = "SELECT k FROM Klient k WHERE k.firma = :firma"),
    @NamedQuery(name = "Klient.loginAutorization", query = "SELECT k FROM Klient k WHERE k.email = :email AND k.haslo = :haslo")
})
public class Klient implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_klient")
    private Integer idKlient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    
    @Size(min = 1, max = 20)
    @Column(name = "haslo")
    private String haslo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Column(name = "firma")
    private Boolean firma;
    @OneToMany(mappedBy = "idKlient", fetch = FetchType.EAGER)
    private Set<Bilet> biletSet;
    @OneToMany(mappedBy = "idKlient", fetch = FetchType.EAGER)
    private Set<Rezerwacja> rezerwacjaSet;

    public Klient()
    {
    }

    public Klient(Integer idKlient)
    {
        this.idKlient = idKlient;
    }

    public Klient(Integer idKlient, String imie, String nazwisko, String haslo, String email)
    {
        this.idKlient = idKlient;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.haslo = haslo;
        this.email = email;
    }

    public Integer getIdKlient()
    {
        return idKlient;
    }

    public void setIdKlient(Integer idKlient)
    {
        this.idKlient = idKlient;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }

    public String getHaslo()
    {
        return haslo;
    }

    public void setHaslo(String haslo)
    {
        this.haslo = haslo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Boolean getFirma()
    {
        return firma;
    }

    public void setFirma(Boolean firma)
    {
        this.firma = firma;
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
    public Set<Rezerwacja> getRezerwacjaSet()
    {
        return rezerwacjaSet;
    }

    public void setRezerwacjaSet(Set<Rezerwacja> rezerwacjaSet)
    {
        this.rezerwacjaSet = rezerwacjaSet;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idKlient != null ? idKlient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klient))
        {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.idKlient == null && other.idKlient != null) || (this.idKlient != null && !this.idKlient.equals(other.idKlient)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Klient[ idKlient=" + idKlient + " ]";
    }
    
}
