package com.digital.signage.dto;

import java.util.Date;

public class PanelDateMapDTO {

    private Long panelId;

    public PanelDateMapDTO(Long panelId, Date date) {
        this.panelId = panelId;
        this.date = date;
    }

    public void setPanelId(Long panelId) {
        this.panelId = panelId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Long getPanelId() {
        return panelId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return ((int) this.date.getTime() + this.panelId.intValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PanelDateMapDTO) {
            PanelDateMapDTO panelDateMapDTO = (PanelDateMapDTO) obj;
            return this.panelId.equals(panelDateMapDTO.panelId) && this.date.equals(panelDateMapDTO.date);
        }
        return false;
    }
}
