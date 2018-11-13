package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	Input<String> _fileName;	
	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		_fileName = _form.addStringInput(Message.openFile());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {

		try {
			_form.parse();
			_receiver.setSaveFile(_fileName.value());
			_receiver.open();
		} catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
