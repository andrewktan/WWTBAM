import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionReader
{
    public static ArrayList < Question > readQuestionsFromFile (String str)
    {
	// create ArrayList of questions
	ArrayList < Question > ret = new ArrayList < Question > ();

	try
	{
	    // create Document object
	    SAXReader reader = new SAXReader ();
	    Document document = reader.read (new FileInputStream ("/home/andrew/WWTBAM/data/" + str));

	    // read data
	    Element root = document.getRootElement ();
	    List questions = root.selectNodes ("/questions/question"); // get list of questions

	    // iterate through questions
	    for (Iterator q = questions.iterator () ; q.hasNext () ;)
	    {
		Element question = (Element) q.next ();

		// get prompt
		String prompt = ((Element) question.selectNodes ("prompt").get (0)).getText ();

		// get answers
		ArrayList < String > answers = new ArrayList < String > ();
		int correct = 0;
		for (Iterator a = question.selectNodes ("answer").iterator () ; a.hasNext () ;)
		{
		    Element answer = (Element) a.next ();
		    answers.add (answer.getText ());

		    // get correct
		    if (answer.attribute ("correct") != null)
			correct = answers.size () - 1;
		}

		// create question object
		ret.add (new Question (prompt, answers, correct));
	    }

	}
	catch (Exception e)
	{
	    e.printStackTrace ();
	}
	return ret;
    }
}

