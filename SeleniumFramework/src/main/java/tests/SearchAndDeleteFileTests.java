package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;

public class SearchAndDeleteFileTests extends BasePage  {

	@Parameters({"fileName", "folderName", "msgSuccessDelete", "msgNoResults"})
	@Test	
	public void deleteItem (
			String fileName, 
			String folderName, 
			String msgSuccessDelete,  
			String msgNoResults) 
	{
		//Pass here the item to delete. It can be either a folder or a file
		BasePage.deleteSought.inputIntoSearchField(folderName);
		BasePage.deleteSought.waitForSearchResult();
		BasePage.deleteSought.triggerActionMenuInResultTableForElement(folderName);
		BasePage.deleteSought.hitDeleteButtonOnActionPanel();
		BasePage.deleteSought.waitForDeleteButtonOnModal();
		BasePage.deleteSought.hitDeleteButtonOnModal();
		BasePage.deleteSought.waitForConfirmationMessage("Delete");
		String result = BasePage.deleteSought.isConfirmationMessageShown(msgSuccessDelete, "Delete");
		Assert.assertEquals(result, msgSuccessDelete, "The confirmation message for success delete was not shown");
		result = BasePage.deleteSought.isNoResultMessageShown(msgNoResults);
		Assert.assertEquals(result, msgNoResults, "The No Results message was not shown");
		Boolean res = BasePage.deleteSought.isSoughtElementShown(folderName);
		Assert.assertFalse(res, "The sought element was found after delete");
	}

	@Parameters({"fileName", "folderName", "msgSuccessUndo"})
	@Test
	public void undoDelete(String fileName, String folderName, String msgSuccessUndo) {
		BasePage.deleteSought.hitUndoButton();
		BasePage.deleteSought.waitForConfirmationMessage("Undo");
		String result = BasePage.deleteSought.isConfirmationMessageShown(msgSuccessUndo, "Undo");
		Assert.assertEquals(result, msgSuccessUndo, "The confirmation message for success undo was not shown");
		Boolean _res = BasePage.deleteSought.isSoughtElementShown(folderName);
		Assert.assertTrue(_res, "The sought element was not found after undo");
	}
}
