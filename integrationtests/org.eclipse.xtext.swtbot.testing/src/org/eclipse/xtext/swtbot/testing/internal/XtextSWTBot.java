/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.swtbot.testing.internal;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.*;

import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTBot to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBot extends SWTBot {

	XtextSWTBot(Control widget) {
		super(widget);
	}

	@Override
	public XtextSWTBotShell shell(String text) {
		return (XtextSWTBotShell) super.shell(text);
	}

	@Override
	public XtextSWTBotShell shell(String text, int index) {
		return new XtextSWTBotShell(shells(text).get(index));
	}

	public XtextSWTBotShell shellWithRegex(String regex) {
		return new XtextSWTBotShell(shellsWithRegex(regex).get(0));
	}

	private List<Shell> shellsWithRegex(String regex) {
		Matcher<Shell> withRegex = withRegex(regex);
		WaitForObjectCondition<Shell> waitForShell = waitForShell(withRegex);
		waitUntilWidgetAppears(waitForShell);
		return waitForShell.getAllMatches();
	}

	@Override
	public XtextSWTBotTree tree() {
		return (XtextSWTBotTree) super.tree();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public XtextSWTBotTree tree(int index) {
		Matcher matcher = allOf(widgetOfType(Tree.class));
		return new XtextSWTBotTree((Tree) widget(matcher, index), matcher);
	}

}
