package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import sth.core.exception.NonEmptySurveyException;
import sth.core.exception.OpeningSurveyException;
import sth.core.exception.SurveyFinishedException;

public class Survey implements Serializable {
	
	private static final long serialVersionUID = 1787291686470022116L;

	enum Condition {
		CREATED,
		OPENED,
		CLOSED,
		FINISHED,
		CANCELED,
	};
	
	private Project _project = null;
	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<Answer>();
	
	private Condition _condition;
	
	Survey(Project project) {
		_project = project;
	}
	
	Condition getCondition() {
		return _condition;
	}
	
	boolean empty() {
		return _answers.size() == 0;
	}
	
	void setCondition(Condition condition) {
		_condition = condition;
	}
	

	void addAnswer(Student student, String message, int hours) {
		if (!_students.contains(student)) {
			_answers.add(new Answer(message, hours));
			_students.add(student);
		}
		// TODO raise exception?
	}
	
	String getSummary(boolean expand) {
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
		
		int med = 0;
		if (_answers.size() > 0)
			med = _answers.stream().mapToInt(Answer::getHours).sum() / _answers.size();
		
		if (expand)
			header += String.format(" - %d respostas - %d horas\n",
					_answers.size(), med);
		
		return header;
	}
	
	String getResultsFor(Person person) {

		String header = getSummary(false);
		
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
		
		if (person instanceof Student) {
			header += "\n * Tempo médio (horas): " + (quant == 0 ? 0 : total);
		} else {
			header += "\n * Tempos de resolução (horas) (mínimo, médio, máximo): " +
						String.format("%d, %d, %d", min, (quant==0?0:total/quant), max);
		}
		return header + '\n';
	}

}
