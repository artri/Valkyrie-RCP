/**
 * Copyright (C) 2015 Valkyrie RCP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.valkyriercp.application.docking;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;
import com.vldocking.swing.docking.DockingConstants;
import org.springframework.core.style.ToStringCreator;
import org.valkyriercp.application.PageComponent;
import org.valkyriercp.application.ViewDescriptor;

import java.awt.*;

/**
 * @author Rogan Dawes
 */
public class ViewDescriptorDockable implements Dockable {

    private ViewDescriptor viewDescriptor;

    private PageComponent pageComponent;

    private DockKey dockKey;

    public ViewDescriptorDockable(ViewDescriptor viewDescriptor) {
        this(viewDescriptor, null);
    }

    public ViewDescriptorDockable(ViewDescriptor viewDescriptor,
            PageComponent pageComponent) {
        this.viewDescriptor = viewDescriptor;
        this.pageComponent = pageComponent;
        this.dockKey = new DockKey(viewDescriptor.getId());

        dockKey.setName(viewDescriptor.getDisplayName());
        dockKey.setTooltip(viewDescriptor.getCaption());
        dockKey.setIcon(viewDescriptor.getIcon());

        boolean autoHideEnabled = VLDockingViewDescriptor.DEFAULT_AUTOHIDEENABLED;
        DockingConstants.Hide autoHideBorder = VLDockingViewDescriptor.DEFAULT_AUTOHIDEBORDER;
        boolean closeEnabled = VLDockingViewDescriptor.DEFAULT_CLOSEENABLED;
        boolean floatEnabled = VLDockingViewDescriptor.DEFAULT_FLOATENABLED;
        boolean maximizeEnabled = VLDockingViewDescriptor.DEFAULT_MAXIMIZEENABLED;
        float resizeWeight = 0;

        if (viewDescriptor instanceof VLDockingViewDescriptor) {
            VLDockingViewDescriptor dockingViewDescriptor = (VLDockingViewDescriptor) viewDescriptor;
            autoHideEnabled = dockingViewDescriptor.isAutoHideEnabled();
            autoHideBorder = dockingViewDescriptor.getAutoHideBorder();
            closeEnabled = dockingViewDescriptor.isCloseEnabled();
            floatEnabled = dockingViewDescriptor.isFloatEnabled();
            maximizeEnabled = dockingViewDescriptor.isMaximizeEnabled();
            resizeWeight = dockingViewDescriptor.getResizeWeight();
        }
        dockKey.setAutoHideEnabled(autoHideEnabled);
        dockKey.setAutoHideBorder(autoHideBorder);
        dockKey.setCloseEnabled(closeEnabled);
        dockKey.setFloatEnabled(floatEnabled);
        dockKey.setMaximizeEnabled(maximizeEnabled);
        dockKey.setResizeWeight(resizeWeight);
    }

    public PageComponent getPageComponent() {
        if (pageComponent == null)
            pageComponent = viewDescriptor.createPageComponent();
        return pageComponent;
    }

    public void setPageComponent(PageComponent pageComponent) {
        this.pageComponent = pageComponent;
    }

    /*
    * (non-Javadoc)
    *
    * @see com.vlsolutions.swing.docking.Dockable#getComponent()
    */
    public Component getComponent() {
        return getPageComponent().getControl();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.vlsolutions.swing.docking.Dockable#getDockKey()
     */
    public DockKey getDockKey() {
        return dockKey;
    }

    public String toString() {
        return new ToStringCreator(this).append("viewDescriptor",
                viewDescriptor.getId()).toString();
    }
}
