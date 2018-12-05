package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.ClosingSurveyIdException;
import sth.core.exception.survey.NonEmptySurveyIdException;
import sth.core.exception.survey.OpeningSurveyIdException;
import sth.core.exception.survey.SurveyFinishedIdException;
import sth.core.exception.survey.NoSurveyIdException;

class Survey implements Serializable {
	
	private static final long serialVersionUID = 1787291686470022116L;

	/** Project associated with the survey. */
	private Project _project = null;
	
	/** All students who have answered the survey. */
	private Set<Student> _students = new HashSet<>();
	
	/**
	 * All answers by all students.
	 * There will only be one answer maximum for each student.
	 * By not using map structures, we preserve each student's privacy.
	 */
	private List<Answer> _answers = new ArrayList<Answer>();
	
	private State _state = State.CREATED;
	
	Survey(Project project) {
		_project = project;
	}
	
	State getState() {
		return _state;
	}
	
	void setState(State state) {
		_state = state;
	}

	Project getProject() {
		return _project;
	}
	
	boolean empty() {
		return _answers.size() == 0;
	}
	
	void addAnswer(Student student, String message, int hours) throws NoSuchProjectIdException, NoSurveyIdException {
		if (!_project.studentSubmited(student))
			throw new NoSuchProjectIdException(_project.getName());
		
		if (!_students.contains(student)) {
			if(_state == State.OPENED){
				_answers.add(new Answer(message, hours));
				_students.add(student);
			}
		}
		else throw new NoSurveyIdException(_project.getDiscipline().getName(), _project.getName());
	}
	
	/**
	 * @return 3 sized array with size, total, minimum, and maximum hours of all answers.
	 */
	private int[] getStats() {
		int total = 0, min = 0, max = 0;
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
		
		return new int[] { quant, total, min, max };
	}
	
	String getResultsForTeacher() {
		
		int[] stats = getStats();
		int quant = stats[0];
		int total = stats[1];
		int min	  = stats[2];
		int max   = stats[3];
		
		String res = getSummary(false);
		
		if (_state == State.FINISHED)
			res += 	"\n * Número de submissões: " + _project.getSubmissions().size()
				+ 	"\n * Número de respostas: " + quant
				+ 	"\n * Tempos de resolução (horas) (mínimo, médio, máximo): " +
					String.format("%d, %d, %d", min, (quant==0?0:total/quant), max);
		
		return res;
	}
	
	String getResultsForStudent() {
		
		int[] stats = getStats();
		int quant = stats[0];
		int total = stats[1];
		
		String res = getSummary(false);
		
		if (_state == State.FINISHED)
			res +=	"\n * Número de respostas: " + quant
				+ 	"\n * Tempo médio (horas): " + (quant == 0 ? 0 : total / quant);
	
		return res;
	}
	
	String getSummary(boolean expand) {
		String header = String.format("%s - %s",
				_project.getDiscipline().getName(),
				_project.getName());
		
		if (_state != State.FINISHED) {
			String state = "por abrir";
			if (_state == State.OPENED)
				state = "aberto";
			else if (_state == State.CLOSED)
				state = "fechado";
			return header + " (" + state + ")\n";
		}
		
		if (expand) {
			int[] stats = getStats();
			int quant = stats[0];
			int total = stats[1];
			header += String.format(" - %d respostas - %d horas\n",
					quant, quant == 0 ? 0 : total / quant);
		}
		
		return header;
	}
	
	void open() throws OpeningSurveyIdException {
		_state.open(this);
	}
	
	void cancel() throws SurveyFinishedIdException, NonEmptySurveyIdException {
		_state.cancel(this);
	}
	
	void close() throws ClosingSurveyIdException {
		_state.close(this);
	}
	
	void finish() throws SurveyFinishedIdException {
		_state.finish(this);
	}
	
	/** State Pattern */
	enum State {
		
		CREATED {

			@Override
			void open(Survey survey) {
				survey.setState(OPENED);
				notifyOpen(survey);
			}

			@Override
			void finish(Survey survey) throws SurveyFinishedIdException{
				throw new SurveyFinishedIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}

			@Override
			void cancel(Survey survey) {
				survey.getProject().setSurvey(null);
			}

			@Override
			void close(Survey survey) throws ClosingSurveyIdException {
				throw new ClosingSurveyIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}
			
		},
		
		OPENED {

			@Override
			void open(Survey survey) {
				// Already opened
			}

			@Override
			void finish(Survey survey) throws SurveyFinishedIdException {
				throw new SurveyFinishedIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}

			@Override
			void cancel(Survey survey) throws NonEmptySurveyIdException {
				if (!survey.empty())
					throw new NonEmptySurveyIdException(
							survey.getProject().getDiscipline().getName(),
							survey.getProject().getName()
							);
				
				survey.getProject().setSurvey(null);
			}

			@Override
			void close(Survey survey) {
				survey.setState(CLOSED);
			}
			
		},
		
		CLOSED {

			@Override
			void open(Survey survey) throws OpeningSurveyIdException {
				throw new OpeningSurveyIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}

			@Override
			void finish(Survey survey) {
				survey.setState(FINISHED);
				notifyFinish(survey);
			}

			@Override
			void cancel(Survey survey) {
				survey.setState(OPENED);
				notifyOpen(survey);
			}

			@Override
			void close(Survey survey) {
				// Already closed
			}
			
		},
		
		FINISHED {

			@Override
			void open(Survey survey) {
				survey.setState(OPENED);
				notifyOpen(survey);
			}

			@Override
			void finish(Survey survey) {
				// Already finished
			}

			@Override
			void cancel(Survey survey) throws SurveyFinishedIdException {
				throw new SurveyFinishedIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}

			@Override
			void close(Survey survey) throws ClosingSurveyIdException {
				throw new ClosingSurveyIdException(
						survey.getProject().getDiscipline().getName(),
						survey.getProject().getName()
						);
			}
			
		};
			
		void notifyOpen(Survey survey) {
			Project proj = survey.getProject();
			Discipline disc = proj.getDiscipline();
			String msg = String.format("Pode preencher inquérito do projecto %s da disciplina %s", proj.getName(), disc.getName());
			disc.notifyPersons(msg);
		}
		
		void notifyFinish(Survey survey) {
			Project proj = survey.getProject();
			Discipline disc = proj.getDiscipline();
			String msg = String.format("Resultados do inquérito do projecto %s da disciplina %s", proj.getName(), disc.getName());
			disc.notifyPersons(msg);
		}
		
		abstract void open(Survey survey)   throws OpeningSurveyIdException;
		abstract void finish(Survey survey) throws SurveyFinishedIdException;
		abstract void cancel(Survey survey) throws SurveyFinishedIdException, NonEmptySurveyIdException;
		abstract void close(Survey survey)  throws ClosingSurveyIdException;
	}
	
}
