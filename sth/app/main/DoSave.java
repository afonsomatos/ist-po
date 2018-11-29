package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {

	/**
	 * @param receiver
	 */
	public DoSave(SchoolManager receiver) {
		super(Label.SAVE, receiver);
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
