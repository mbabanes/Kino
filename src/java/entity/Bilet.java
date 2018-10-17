/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mbabane
 */
@Entity
@Table(name = "bilet")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Bilet.findAll", query = "SELECT b FROM Bilet b"),
    @NamedQuery(name = "Bilet.findByIdBilet", query = "SELECT b FROM Bilet b WHERE b.idBilet = :idBilet")
})
public class Bilet implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bilet")
    private Integer idBilet;
    @JoinColumn(name = "id_klient", referencedColumnName = "id_klient")
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient idKlient;
    @JoinColumn(name = "id_miejsca", referencedColumnName = "id_miejsca")
    @ManyToOne(fetch = FetchType.EAGER)
    private Miejsca idMiejsca;
    @JoinColumn(name = "id_pracownik", referencedColumnName = "id_pracownik")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pracownik idPracownik;
    @JoinColumn(name = "id_rodz_bi", referencedColumnName = "id_rodz_bi")
    @ManyToOne(fetch = FetchType.EAGER)
    private RodzajBiletu idRodzBi;
    @JoinColumn(name = "id_seans", referencedColumnName = "id_seans")
    @ManyToOne(fetch = FetchType.EAGER)
    private Seans idSeans;

    public Bilet()
    {
    }

    public Bilet(Integer idBilet)
    {
        this.idBilet = idBilet;
    }

    public Integer getIdBilet()
    {
        return idBilet;
    }

    public void setIdBilet(Integer idBilet)
    {
        this.idBilet = idBilet;
    }

    public Klient getIdKlient()
    {
        return idKlient;
    }

    public void setIdKlient(Klient idKlient)
    {
        this.idKlient = idKlient;
    }

    public Miejsca getIdMiejsca()
    {
        return idMiejsca;
    }

    public void setIdMiejsca(Miejsca idMiejsca)
    {
        this.idMiejsca = idMiejsca;
    }

    public Pracownik getIdPracownik()
    {
        return idPracownik;
    }

    public void setIdPracownik(Pracownik idPracownik)
    {
        this.idPracownik = idPracownik;
    }

    public RodzajBiletu getIdRodzBi()
    {
        return idRodzBi;
    }

    public void setIdRodzBi(RodzajBiletu idRodzBi)
    {
        this.idRodzBi = idRodzBi;
    }

    public Seans getIdSeans()
    {
        return idSeans;
    }

    public void setIdSeans(Seans idSeans)
    {
        this.idSeans = idSeans;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idBilet != null ? idBilet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bilet))
        {
            return false;
        }
        Bilet other = (Bilet) object;
        if ((this.idBilet == null && other.idBilet != null) || (this.idBilet != null && !this.idBilet.equals(other.idBilet)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Bilet[ idBilet=" + idBilet + " ]";
    }
    
}
