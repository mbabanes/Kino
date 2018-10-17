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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "sale")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findByIdSali", query = "SELECT s FROM Sale s WHERE s.idSali = :idSali"),
    @NamedQuery(name = "Sale.findByIloscrzedow", query = "SELECT s FROM Sale s WHERE s.iloscrzedow = :iloscrzedow"),
    @NamedQuery(name = "Sale.findByIloscMiejscWrzedzie", query = "SELECT s FROM Sale s WHERE s.iloscMiejscWrzedzie = :iloscMiejscWrzedzie")
})
public class Sale implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sali")
    private Integer idSali;
    @Column(name = "Ilosc_rzedow")
    private Integer iloscrzedow;
    @Column(name = "Ilosc_Miejsc_W_rzedzie")
    private Integer iloscMiejscWrzedzie;
    @JoinColumn(name = "Id_Lokalicacja", referencedColumnName = "Id_Lokalicacja")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lokalizacja idLokalicacja;
    @JoinColumn(name = "Id_Pracownik", referencedColumnName = "id_pracownik")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pracownik idPracownik;
    @OneToMany(mappedBy = "idSali", fetch = FetchType.EAGER)
    private Set<Seans> seansSet;

    public Sale()
    {
    }

    public Sale(Integer idSali)
    {
        this.idSali = idSali;
    }

    public Integer getIdSali()
    {
        return idSali;
    }

    public void setIdSali(Integer idSali)
    {
        this.idSali = idSali;
    }

    public Integer getIloscrzedow()
    {
        return iloscrzedow;
    }

    public void setIloscrzedow(Integer iloscrzedow)
    {
        this.iloscrzedow = iloscrzedow;
    }

    public Integer getIloscMiejscWrzedzie()
    {
        return iloscMiejscWrzedzie;
    }

    public void setIloscMiejscWrzedzie(Integer iloscMiejscWrzedzie)
    {
        this.iloscMiejscWrzedzie = iloscMiejscWrzedzie;
    }

    public Lokalizacja getIdLokalicacja()
    {
        return idLokalicacja;
    }

    public void setIdLokalicacja(Lokalizacja idLokalicacja)
    {
        this.idLokalicacja = idLokalicacja;
    }

    public Pracownik getIdPracownik()
    {
        return idPracownik;
    }

    public void setIdPracownik(Pracownik idPracownik)
    {
        this.idPracownik = idPracownik;
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
        hash += (idSali != null ? idSali.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale))
        {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.idSali == null && other.idSali != null) || (this.idSali != null && !this.idSali.equals(other.idSali)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Sale[ idSali=" + idSali + " ]";
    }
    
}
