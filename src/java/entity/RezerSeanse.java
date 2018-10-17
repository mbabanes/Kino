/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "rezer_seanse")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RezerSeanse.findAll", query = "SELECT r FROM RezerSeanse r"),
    @NamedQuery(name = "RezerSeanse.findByIdRezerwacja", query = "SELECT r FROM RezerSeanse r WHERE r.rezerSeansePK.idRezerwacja = :idRezerwacja"),
    @NamedQuery(name = "RezerSeanse.findByIdMiejsca", query = "SELECT r FROM RezerSeanse r WHERE r.rezerSeansePK.idMiejsca = :idMiejsca")
})
public class RezerSeanse implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RezerSeansePK rezerSeansePK;
    @JoinColumn(name = "id_miejsca", referencedColumnName = "id_miejsca", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Miejsca miejsca;
    @JoinColumn(name = "id_rezerwacja", referencedColumnName = "id_rezerwacja", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rezerwacja rezerwacja;
    @JoinColumn(name = "id_seans", referencedColumnName = "id_seans")
    @ManyToOne(fetch = FetchType.EAGER)
    private Seans idSeans;
    
    @JoinColumn(name = "id_rodz_bi", referencedColumnName = "id_rodz_bi")
    @ManyToOne(fetch = FetchType.EAGER)
    private RodzajBiletu idRodzBi;

    public RezerSeanse()
    {
    }

    public RezerSeanse(RezerSeansePK rezerSeansePK)
    {
        this.rezerSeansePK = rezerSeansePK;
    }

    public RezerSeanse(int idRezerwacja, int idMiejsca)
    {
        this.rezerSeansePK = new RezerSeansePK(idRezerwacja, idMiejsca);
    }

    public RezerSeansePK getRezerSeansePK()
    {
        return rezerSeansePK;
    }

    public void setRezerSeansePK(RezerSeansePK rezerSeansePK)
    {
        this.rezerSeansePK = rezerSeansePK;
    }

    public Miejsca getMiejsca()
    {
        return miejsca;
    }

    public void setMiejsca(Miejsca miejsca)
    {
        this.miejsca = miejsca;
    }

    public Rezerwacja getRezerwacja()
    {
        return rezerwacja;
    }

    public void setRezerwacja(Rezerwacja rezerwacja)
    {
        this.rezerwacja = rezerwacja;
    }

    public Seans getIdSeans()
    {
        return idSeans;
    }

    public void setIdSeans(Seans idSeans)
    {
        this.idSeans = idSeans;
    }
    
    
    public RodzajBiletu getIdRodzBi()
    {
        return idRodzBi;
    }
    
    public void setIdRodzBi(RodzajBiletu idRodzBi)
    {
        this.idRodzBi = idRodzBi;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (rezerSeansePK != null ? rezerSeansePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RezerSeanse))
        {
            return false;
        }
        RezerSeanse other = (RezerSeanse) object;
        if ((this.rezerSeansePK == null && other.rezerSeansePK != null) || (this.rezerSeansePK != null && !this.rezerSeansePK.equals(other.rezerSeansePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.RezerSeanse[ rezerSeansePK=" + rezerSeansePK + " ]";
    }
    
}
