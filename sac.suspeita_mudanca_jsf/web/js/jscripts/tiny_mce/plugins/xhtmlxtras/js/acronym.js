 /**
 * $Id: acronym.js,v 1.1 2012-05-11 18:48:35 cvs_user Exp $
 *
 * @author Moxiecode - based on work by Andrew Tetlaw
 * @copyright Copyright 2004-2008, Moxiecode Systems AB, All rights reserved.
 */

function init() {
	SXE.initElementDialog('acronym');
	if (SXE.currentAction == "update") {
		SXE.showRemoveButton();
	}
}

function insertAcronym() {
	SXE.insertElement('acronym');
	tinyMCEPopup.close();
}

function removeAcronym() {
	SXE.removeElement('acronym');
	tinyMCEPopup.close();
}

tinyMCEPopup.onInit.add(init);
