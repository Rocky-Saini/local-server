package com.digital.signage.util

import com.digital.signage.models.PanelExt

/**
 * @author -Ravi Kumar created on 1/24/2023 11:00 PM
 * @project - Digital Sign-edge
 */
class PanelExtComparator : Comparator<PanelExt> {

    override fun compare(panelExt1: PanelExt, panelExt2: PanelExt): Int {
        return panelExt1.panelName.uppercase().compareTo(panelExt2.panelName.uppercase())
    }
}