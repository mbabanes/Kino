/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mbabane
 */
@Embeddable
public class RezerSeansePK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rezerwacja")
    private int idRezerwacja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_miejsca")
    private int idMiejsca;

    public RezerSeansePK()
    {
    }

    public RezerSeansePK(int idRezerwacja, int idMiejsca)
    {
        this.idRezerwacja = idRezerwacja;
        this.idMiejsca = idMiejsca;
    }

    public int getIdRezerwacja()
    {
        return idRezerwacja;
    }

    public void setIdRezerwacja(int idRezerwacja)
    {
        this.idRezerwacja = idRezerwacja;
    }

    public int getIdMiejsca()
    {
        return idMiejsca;
    }

    public void setIdMiejsca(int idMiejsca)
    {
        this.idMiejsca = idMiejsca;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idRezerwacja;
        hash += (int) idMiejsca;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RezerSeansePK))
        {
            return false;
        }
        RezerSeansePK other = (RezerSeansePK) object;
        if (this.idRezerwacja != other.idRezerwacja)
        {
            return false;
        }
        if (this.idMiejsca != other.idMiejsca)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.RezerSeansePK[ idRezerwacja=" + idRezerwacja + ", idMiejsca=" + idMiejsca + " ]";
    }
    
}
