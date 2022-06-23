package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soup().getProjects();
    System.out.println(projects.size());
    projects.forEach(x -> System.out.println(x.getName()));
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soup().getProjects();
    Issue issue = new Issue().setSummary("Summary issue")
        .setDescription("Description issue")
        .setProject(projects.iterator().next());
    Issue issue_new = app.soup().addIssue(issue);
    assertEquals(issue.getSummary(), issue_new.getSummary());
  }

  @Test(testName = "Для задания №19")
  public void testIgnoreNotFixedIssue() throws MalformedURLException, ServiceException, RemoteException {
    skipNotFixed(BigInteger.valueOf(1));
    System.out.println("готово");
  }
}
