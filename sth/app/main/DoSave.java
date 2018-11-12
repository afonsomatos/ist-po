package sth.app.main;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
	//FIXME add input fields if needed

	/**
	 * @param receiver
	 */
	public DoSave(SchoolManager receiver) {
		super(Label.SAVE, receiver);
		//FIXME initialize input fields if needed
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {

		try {
			if (!_receiver.hasSaveFile()) {
				Input<String> filename = _form.addStringInput(Message.newSaveAs());
				_form.parse();
				_receiver.setSaveFile(filename.value());
			}
			_receiver.save();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
