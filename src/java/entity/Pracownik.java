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
@Table(name = "pracownik")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Pracownik.findAll", query = "SELECT p FROM Pracownik p"),
    @NamedQuery(name = "Pracownik.findByIdPracownik", query = "SELECT p FROM Pracownik p WHERE p.idPracownik = :idPracownik"),
    @NamedQuery(name = "Pracownik.findByNazwisko", query = "SELECT p FROM Pracownik p WHERE p.nazwisko = :nazwisko"),
    @NamedQuery(name = "Pracownik.findByImie", query = "SELECT p FROM Pracownik p WHERE p.imie = :imie"),
    @NamedQuery(name = "Pracownik.findByWynagordzenie", query = "SELECT p FROM Pracownik p WHERE p.wynagordzenie = :wynagordzenie"),
    @NamedQuery(name = "Pracownik.findByStanowisko", query = "SELECT p FROM Pracownik p WHERE p.stanowisko = :stanowisko")
})
public class Pracownik implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pracownik")
    private Integer idPracownik;
    @Size(max = 40)
    @Column(name = "Nazwisko")
    private String nazwisko;
    @Size(max = 40)
    @Column(name = "Imie")
    private String imie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "wynagordzenie")
    private BigDecimal wynagordzenie;
    @Size(max = 20)
    @Column(name = "stanowisko")
    private String stanowisko;
    @OneToMany(mappedBy = "idPracownik", fetch = FetchType.EAGER)
    private Set<Bilet> biletSet;
    @JoinColumn(name = "Id_Lokalicacja", referencedColumnName = "Id_Lokalicacja")
    @ManyToOne(fetch = FetchType.EAGER)
    private Lokalizacja idLokalicacja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPracownik", fetch = FetchType.EAGER)
    private Set<Sale> saleSet;
    

    public Pracownik()
    {
    }

    public Pracownik(Integer idPracownik)
    {
        this.idPracownik = idPracownik;
    }

    public Integer getIdPracownik()
    {
        return idPracownik;
    }

    public void setIdPracownik(Integer idPracownik)
    {
        this.idPracownik = idPracownik;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public BigDecimal getWynagordzenie()
    {
        return wynagordzenie;
    }

    public void setWynagordzenie(BigDecimal wynagordzenie)
    {
        this.wynagordzenie = wynagordzenie;
    }

    public String getStanowisko()
    {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko)
    {
        this.stanowisko = stanowisko;
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

    public Lokalizacja getIdLokalicacja()
    {
        return idLokalicacja;
    }

    public void setIdLokalicacja(Lokalizacja idLokalicacja)
    {
        this.idLokalicacja = idLokalicacja;
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

 

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPracownik != null ? idPracownik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pracownik))
        {
            return false;
        }
        Pracownik other = (Pracownik) object;
        if ((this.idPracownik == null && other.idPracownik != null) || (this.idPracownik != null && !this.idPracownik.equals(other.idPracownik)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Pracownik[ idPracownik=" + idPracownik + " ]";
    }
    
}
