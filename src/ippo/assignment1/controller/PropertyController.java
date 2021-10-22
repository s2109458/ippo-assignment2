// IPPO Assignment 1, Version 20.3, 21/12/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.HashMap;
import ippo.assignment1.library.utils.Properties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;

/**
 * A simple controller for the PictureViewer application.
 * 
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/12/2020
 */
public class PropertyController implements Controller {

	private View view;
	private Service service;

	//create a hashmap to store selection and name
	HashMap<Integer,String> munroTimes = new HashMap<Integer,String>();

	/**
	 * Start the controller.
	 */
	//takes the name of a Munro, (a) adds a selection to the
	//interface, and (b) adds a corresponding entry to the HashMap
	public int addSubject(String name){
		return view.addSelection(name);
	}

	public void start() {
		//System.out.println(this.sub);
		// create the view and the service objects
		view = new ViewFromProperties(this);
		service = new ServiceFromProperties();

		//a list of picture names
		String[] subjectList = Properties.get("controller.subjects").split(",");

		// divide the list to single picture name
		for (String subject : subjectList) {
			subject = subject.trim();
			int selectionID = addSubject(subject);
			// add selection identifier and name to the hashmap
			munroTimes.put(selectionID,subject);
		}

		// start the interface
		view.start();
	}

	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item
	 */
	public void select(int selectionID) {
		
		// a picture corresponding to the selectionID
		// by default, this is an empty picture
		// (this is used if the selectionID does not match)
		Picture picture = new Picture();

		// create a picture corresponding to the selectionID
		picture = service.getPicture(munroTimes.get(selectionID),1);

		// show the picture in the interface
		view.showPicture(picture);
	}
}
