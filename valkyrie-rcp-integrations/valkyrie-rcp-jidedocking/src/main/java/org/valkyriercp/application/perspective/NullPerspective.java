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
package org.valkyriercp.application.perspective;

import com.jidesoft.docking.DockingManager;

/**
 * A null, or do nothing, implementation of the perspective
 * concept.
 * 
 * @author Jonny Wray
 *
 */
public class NullPerspective extends Perspective {

	public static final NullPerspective NULL_PERSPECTIVE = new NullPerspective();
	private static final String ID = "nullPerspective";
	
	private NullPerspective(){
		super(ID);
	}
	
	public void display(DockingManager manager) {
		// null, so do nothing
	}

}
