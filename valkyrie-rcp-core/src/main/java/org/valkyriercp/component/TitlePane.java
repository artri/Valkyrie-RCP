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
package org.valkyriercp.component;

import com.jgoodies.forms.factories.FormFactory;
import org.jdesktop.swingx.JXPanel;
import org.valkyriercp.core.Message;
import org.valkyriercp.core.TitleConfigurable;
import org.valkyriercp.factory.AbstractControlFactory;
import org.valkyriercp.image.config.ImageConfigurable;
import org.valkyriercp.layout.TableLayoutBuilder;
import org.valkyriercp.util.ValkyrieRepository;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

/**
 * A container class that that has a title area for displaying a title and an
 * image as well as a common area for displaying a description, a message, or an
 * error message.
 */
public class TitlePane extends AbstractControlFactory implements MessagePane,
		TitleConfigurable, ImageConfigurable {

	/**
	 * Image source key for banner image (value <code>dialog_title_banner</code>
	 * ).
	 */
	public static final String DEFAULT_TITLE_IMAGE = "titledDialog.image";

	private String title = "Title Pane Title";

	private Image image;

	private JLabel titleLabel;

	private JLabel iconLabel;

	private MessagePane messagePane;

	public TitlePane() {
		this(DefaultMessageAreaPane.DEFAULT_LINES_TO_DISPLAY);
	}

	public TitlePane(int linesToDisplay) {
		this.messagePane = new DefaultMessageAreaPane(linesToDisplay, this);
	}

	public void setTitle(String newTitle) {
		if (newTitle == null) {
			newTitle = "";
		}
		this.title = newTitle;
		if (isControlCreated()) {
			titleLabel.setText(newTitle);
		}
	}

	public void setImage(Image image) {
		this.image = image;
		if (isControlCreated()) {
			iconLabel.setIcon(getIcon());
		}
	}

    public Color getTitlePaneBackgroundColor() {
        return ValkyrieRepository.getInstance().getApplicationConfig().titlePaneBackgroundColor();
    }

    public Color getTitlePanePinstripeColor() {
        return ValkyrieRepository.getInstance().getApplicationConfig().titlePanePinstripeColor();
    }

    protected JComponent createControl() {
		titleLabel = new JLabel();
		titleLabel.setName("title");
		titleLabel.setOpaque(false);
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
		titleLabel.setText(title);

		iconLabel = new JLabel();
		iconLabel.setName("icon");
		iconLabel.setBackground(getBackgroundColor());
		iconLabel.setIcon(getIcon());

		JXPanel panel = new JXPanel();
		panel.setName("panel");
        ValkyrieRepository.getInstance().getApplicationConfig().titlePaneConfigurer().configure(panel);

		TableLayoutBuilder table = new TableLayoutBuilder(panel);
		table.row(FormFactory.LINE_GAP_ROWSPEC);
		table.gapCol();
		table.cell(titleLabel);
		table.gapCol();
		table.cell(iconLabel, "rowspan=2 colspec=pref");
		table.gapCol();
		table.row(FormFactory.NARROW_LINE_GAP_ROWSPEC);
		table.cell(messagePane.getControl());
		table.row(FormFactory.NARROW_LINE_GAP_ROWSPEC);
		return table.getPanel();
	}

	private Icon getIcon() {
		if (image != null)
			return new ImageIcon(image);

		return null;
		// return new
		// ImageIcon(applicationConfig.imageSource().getImage(DEFAULT_TITLE_IMAGE));
	}

	private Color getBackgroundColor() {
		Color c = UIManager.getLookAndFeel().getDefaults()
				.getColor("primaryControlHighlight");
		if (c == null) {
			c = UIManager.getColor("controlLtHighlight");
		}
		return c;
	}

	public boolean isMessageShowing() {
		return messagePane.isMessageShowing();
	}

	public Message getMessage() {
		return messagePane.getMessage();
	}

	public void setMessage(Message newMessage) {
		messagePane.setMessage(newMessage);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		messagePane.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		messagePane.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		messagePane.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		messagePane.removePropertyChangeListener(propertyName, listener);
	}

	public String getTitle() {
		return title;
	}

	public Image getImage() {
		return image;
	}
}
