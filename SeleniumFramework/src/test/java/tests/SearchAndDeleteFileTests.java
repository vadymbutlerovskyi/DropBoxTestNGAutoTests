package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;

public class SearchAndDeleteFileTests extends BasePage  {
	
	@Parameters({"fileName", "folderName", "msgSuccessDelete", "msgSuccessUndo", "msgNoResults"})
	@Test	
	public void deleteItem (
			String fileName, 
			String folderName, 
			String msgSuccessDelete, 
			String msgSuccessUndo, 
			String msgNoResults) 
	{
		//Pass here the item to delete. It can be either a folder or a file
		BasePage.deleteSought.inputIntoSearchField(folderName);
		BasePage.deleteSought.waitForSearchResult();
		BasePage.deleteSought.triggerActionMenuInResultTableForElement(folderName);
		BasePage.deleteSought.hitDeleteButtonOnActionPanel();
		BasePage.deleteSought.waitForDeleteButtonOnModal();
		BasePage.deleteSought.hitDeleteButtonOnModal();
		BasePage.deleteSought.waitForConfirmationMessage();
		String result = BasePage.deleteSought.isConfirmationMessageShown(msgSuccessDelete);
		Assert.assertEquals(result, msgSuccessDelete, "The confirmation message for success delete was not shown"); //put messages to Assertions!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		result = BasePage.deleteSought.isNoResultMessageShown(msgNoResults);
		Assert.assertEquals(result, msgNoResults, "The No Results message was not shown");
	}
	
	@Parameters({"fileName", "folderName"})
	@Test
	public void undoDelete(String fileName, String folderName) {
		BasePage.deleteSought.hitUndoButton();
		BasePage.deleteSought.waitForConfirmationMessage();
		Boolean result = BasePage.deleteSought.isSoughtElementShown(folderName);
		Assert.assertTrue(result, "The sought element was not found after undo");
	}
}
