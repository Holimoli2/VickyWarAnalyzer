package ee.tkasekamp.vickywaranalyzer.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import ee.tkasekamp.vickywaranalyzer.controller.tab.BattleController;
import ee.tkasekamp.vickywaranalyzer.controller.tab.SettingsController;
import ee.tkasekamp.vickywaranalyzer.controller.tab.WarDetailsController;
import ee.tkasekamp.vickywaranalyzer.controller.tab.WarListController;
import ee.tkasekamp.vickywaranalyzer.controller.tab.WargoalController;
import ee.tkasekamp.vickywaranalyzer.service.ModelService;
import ee.tkasekamp.vickywaranalyzer.service.ModelServiceImpl;
import ee.tkasekamp.vickywaranalyzer.service.UtilService;
import ee.tkasekamp.vickywaranalyzer.service.UtilServiceImpl;

public class MainController {
	@FXML
	private BattleController battleController;
	@FXML
	private SettingsController settingsController;
	@FXML
	private WarDetailsController warDetailsController;
	@FXML
	private WargoalController wargoalController;
	@FXML
	private WarListController warListController;
	@FXML
	private Tab warListTab;
	@FXML
	private Tab warDetailsTab;
	@FXML
	private Tab battleTab;
	@FXML
	private Tab wargoalTab;

	private ModelService modelServ;
	private UtilService utilServ;

	@FXML
	public void initialize() {
		modelServ = new ModelServiceImpl();
		utilServ = new UtilServiceImpl();
		try {
			utilServ.guessFolders();
		} catch (IOException e) {
			setErrorText(e.getMessage());
		}

		battleController.init(this);
		settingsController.init(this, utilServ);
		warDetailsController.init(this);
		wargoalController.init(this);
		warListController.init(this);

	}

	public void setErrorText(String text) {
		settingsController.setErrorText(text);
	}

	public void readSaveGame(String saveGamePath) {
		reset();
		modelServ.parseSaveGame(saveGamePath);
		try {
			utilServ.writePathsToFile();
		} catch (IOException e) {
			setErrorText(e.getMessage());
		}
	}

	private void reset() {
		battleController.reset();
		settingsController.reset();
		warDetailsController.reset();
		wargoalController.reset();
		warListController.reset();

		warDetailsTab.setText("War");
		battleTab.setText("Battle");
		warListTab.setDisable(true);
		battleTab.setDisable(true);
		wargoalTab.setDisable(true);

		modelServ.reset();
		utilServ.reset();
	}
}
