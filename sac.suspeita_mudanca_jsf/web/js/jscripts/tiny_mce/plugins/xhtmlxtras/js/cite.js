 /**
 * $Id: cite.js,v 1.1 2012-05-11 18:48:35 cvs_user Exp $
 *
 * @author Moxiecode - based on work by Andrew Tetlaw
 * @copyright Copyright 2004-2008, Moxiecode Systems AB, All rights reserved.
 */

function init() {
	SXE.initElementDialog('cite');
	if (SXE.currentAction == "update") {
		SXE.showRemoveButton();
	}
}

function insertCite() {
	SXE.insertElement('cite');
	tinyMCEPopup.close();
}

function removeCite() {
	SXE.removeElement('cite');
	tinyMCEPopup.close();
}

tinyMCEPopup.onInit.add(init);
