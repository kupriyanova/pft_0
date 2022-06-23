package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoupHelper {

  private String SOUP_ADMIN;
  private String SOUP_PASSWORD;

  private final ApplicationManager app;

  public SoupHelper(ApplicationManager app) {
    this.app = app;
    SOUP_ADMIN = app.getProperty("soup.adminLogin");
    SOUP_PASSWORD = app.getProperty("soup.adminPassword");
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream()
        .map(p -> new Project().setId(p.getId()).setName(p.getName()))
        .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator()
        .getMantisConnectPort(new URL("http://localhost/mantisbt/api/soap/mantisconnect.php"));
    return mc;
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(
                          issue.getProject().getId(),
                          issue.getProject().getName()));
    issueData.setCategory(mc.mc_project_get_categories(SOUP_ADMIN, SOUP_PASSWORD, issue.getProject().getId())[0]);
    BigInteger issueId = mc.mc_issue_add(SOUP_ADMIN, SOUP_PASSWORD, issueData);
    IssueData createdIssueData = mc.mc_issue_get(SOUP_ADMIN, SOUP_PASSWORD, issueId);
    return new Issue().setId(createdIssueData.getId())
        .setSummary(createdIssueData.getSummary())
        .setDescription(createdIssueData.getDescription())
        .setProject(new Project().setId(createdIssueData.getProject().getId())
            .setName(createdIssueData.getProject().getName()));
  }

  public IssueData getIssueData(BigInteger issueId) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get(SOUP_ADMIN, SOUP_PASSWORD, issueId);
  }
}
