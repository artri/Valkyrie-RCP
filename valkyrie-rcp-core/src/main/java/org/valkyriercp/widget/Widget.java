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
package org.valkyriercp.widget;

import org.valkyriercp.application.PageComponentContext;
import org.valkyriercp.command.support.AbstractCommand;

import javax.swing.*;
import java.util.List;

/**
 * Widget specifies the interface for Swing component factories
 *
 * General usage of implementations:
 * <ol>
 * <li>Instantiate the widget</li>
 * <li>Set specific parameters for the widget or use overloaded constructors
 * to combine this step and the previous one</li>
 * <li>Get the component using {@link #getComponent()}</li>
 * </ol>
 */
public interface Widget
{
    static Widget EMPTY_WIDGET = new AbstractWidget(){

        public JComponent getComponent()
        {
            return new JPanel();
        }

        @Override
        public String getId() {
            return "emptyWidget";
        }
    };

    static WidgetProvider<Widget> EMPTY_WIDGET_PROVIDER = new WidgetProvider<Widget>(){

        @Override
        public Widget getWidget() {
            return EMPTY_WIDGET;
        }
    };


    /**
     * @return A not <code>null</code> graphical component built using the
     * parameters held in the widget instance.
     */
    public JComponent getComponent();

    /**
     * Hook method called before showing the component on screen.
     */
    public void onAboutToShow();

    /**
     * Hook method called before moving the component to the background (=hiding)
     */
    public void onAboutToHide();

    /**
     * Encompasses the status of the widget between onAboutToShow() and onAboutToHide().
     * A component does not know when it's active, even if there is an isShowing() method
     * in it's API (due to tab panels or scroll panes).
     *
     * @return <code>true</code> if the widget is shown in the foreground.
     */
    public boolean isShowing();

    /**
     * Checks whether this component can be closed visually, for example when there
     * are no unsaved changes.
     *
     * @return <code>true</code> if the widget can be closed without problems.
     */
    public boolean canClose();

    /**
     * Returns a list of commands for this widget.
     */
    public List<? extends AbstractCommand> getCommands();

    public String getId();

    public void registerLocalCommandExecutors(PageComponentContext context);
}
