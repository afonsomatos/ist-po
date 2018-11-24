package sth.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Survey {
	
	private enum Condition {
		CREATED,
		OPENED,
		CLOSED,
		FINALIZED,
		CANCELED
	};
	
	private Project _project = null;
	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<Answer>();
	
	private Condition _condition;
	
	Survey(Project project) {
		_project = project;
	}
	
	void open() {
		// TODO
	}
	
	void close() {
		// TODO
	}
	
	void doFinalize() {
		// TODO
	}
	
	void addAnswer(Student student, String message, int hours) {
		if (!_students.contains(student))
			_answers.add(new Answer(message, hours));
		// TODO raise exception?
	}
	
	String getResultsFor(Person person) {

		List<Condition> active = Arrays.asList(
				Condition.OPENED,
				Condition.CLOSED, 
				Condition.CREATED);
		
		String header = String.format("%s - %s",
				_project.getDiscipline().getName(),
				_project.getName());
		
		if (active.contains(_condition)) {
			String state = "por abrir";
			if (_condition == Condition.OPENED)
				state = "aberto";
			else if (_condition == Condition.CLOSED)
				state = "fechado";
			return header + " (" + state + ")\n";
		}
		
		int total = 0,
			min = 0,
			max = 0;
		
		int quant = _answers.size();
		
		if (quant > 0) {
			min = Integer.MAX_VALUE;
			for (Answer a : _answers) {
				int h = a.getHours();
				total += h;
				if (h < min) min = h;
				if (h > max) max = h;
			}
		}
		
		if (person instanceof Teacher) {
			header += "\n * Número de submissões: " + _project.getSubmissions().size();
		}
		
		header += "\n * Número de respostas: " + quant;
		header += "\n * Tempos de resolução: (horas) (mínimo, médio, máximo): " +
						String.format("%d, %d, %d", min, total/quant, max);
		header += "\n";
		return header;
	}

}
